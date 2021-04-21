package cn.tinybee.ke.biz.cms.service.impl;

import cn.tinybee.ke.biz.cms.dto.CmsCourseDto;
import cn.tinybee.ke.biz.cms.dto.CourseSearchParamDto;
import cn.tinybee.ke.biz.cms.entity.CmsCourse;
import cn.tinybee.ke.biz.cms.entity.CmsCourseIntro;
import cn.tinybee.ke.biz.cms.mapper.CmsCourseMapper;
import cn.tinybee.ke.biz.cms.mongo.CmsCourseDetailRepository;
import cn.tinybee.ke.biz.cms.service.*;
import cn.tinybee.ke.common.entity.GroupCount;
import cn.tinybee.ke.common.entity.Operator;
import cn.tinybee.ke.common.exception.BusinessException;
import cn.tinybee.ke.common.util.AssertUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

/**
 * <p>
 * 内容主表 服务实现类
 * </p>
 *
 * @author hao.huang
 * @since 2020-04-10
 */
@Service
public class CmsCourseServiceImpl extends ServiceImpl<CmsCourseMapper, CmsCourse> implements ICmsCourseService {

    @Autowired
    private ICmsCourseIntroService iCmsCourseIntroService;

    @Autowired
    private Mapper mapper;

    @Autowired
    private CmsCourseDetailRepository cmsCourseDetailRepository;

    @Autowired
    private ICmsCourseClassifyService iCmsCourseClassifyService;

    @Autowired
    private ICmsClassifyService iCmsClassifyService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean saveOrUpdateContent(Operator user, CmsCourseDto content) {
        // 处理状态

        LocalDateTime now = LocalDateTime.now();
        if (content.getId() == null) {
            content.setCreator(user.getId());
            content.setCreateTime(now);
        }
        // 设置 上架
        setOnShelfInfo(content);

        content.setModifier(user.getId());
        content.setModifyTime(now);

        this.saveOrUpdate(content);
        QueryWrapper<CmsCourseIntro> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_id",content.getId());
        CmsCourseIntro contentIntro = iCmsCourseIntroService.getOne(queryWrapper);
        if (contentIntro != null ) {
            contentIntro.setDescription(content.getDescription());
            iCmsCourseIntroService.updateById(contentIntro);

        }else {
            contentIntro = new CmsCourseIntro();
            contentIntro.setDescription(content.getDescription());
            contentIntro.setCourseId(content.getId());
            contentIntro.setCreateTime(LocalDateTime.now());
            contentIntro.setCreator(content.getCreator());
            iCmsCourseIntroService.save(contentIntro);
        }
        //
        iCmsCourseClassifyService.saveRelation(content.getId(), content.getDirectionIds(), 1);
        iCmsCourseClassifyService.saveRelation(content.getId(), content.getClassifyIds(), 2);
        // 保存信息
        return true;
    }

    private void setOnShelfInfo (CmsCourse cmsCourse) {
        Integer status = cmsCourse.getStatus();
        if (status == null || status == 1) { // 只有在待上架的时候 才会去做
            Integer onShelfType = cmsCourse.getOnShelfType();
            AssertUtils.notNull(onShelfType, "请选择上架方式");
            if (onShelfType == 1){
                cmsCourse.setStatus(CmsCourse.ON_SHELF);
                if (cmsCourse.getId() == null) {
                    cmsCourse.setActualOnShelfTime(LocalDateTime.now());
                }
            }
        }
    }


    @Override
    public CmsCourseDto get(Long id) {
        CmsCourse content = this.getById(id);
        if (content == null) {
            return null;
        }
        CmsCourseDto res = mapper.map(content, CmsCourseDto.class);
        QueryWrapper<CmsCourseIntro> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_id",content.getId());
        CmsCourseIntro contentIntro = iCmsCourseIntroService.getOne(queryWrapper);
        if (contentIntro != null) {
            res.setDescription(contentIntro.getDescription());
        }
        Map<Integer, List<Long>> relationIds = iCmsCourseClassifyService.getRelationIds(id);
        res.setDirectionIds(relationIds.getOrDefault(1, Collections.emptyList()));
        res.setClassifyIds(relationIds.getOrDefault(2, Collections.emptyList()));
        return res;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean delete(Operator operator, Long id) {
        // 判断是否被别人购买 如果再订单中已经存在 则不能删除  有学习记录的

        /**
         * 删除课程 删除关联内容
         * 删除 章节
         * 删除 课时
         */
        iCmsCourseIntroService.deleteByCourseId(id);
        this.removeById(id);
        return true;
    }

    @Override
    public CmsCourseDto getDetailForWeb(Long currentUserId, Long id) {
        CmsCourse content = baseMapper.getDetailById(id);
        AssertUtils.notNull(content, "无效课程");
        CmsCourseDto res = mapper.map(content, CmsCourseDto.class);
        QueryWrapper<CmsCourseIntro> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_id",content.getId());
        CmsCourseIntro contentIntro = iCmsCourseIntroService.getOne(queryWrapper);
        if (contentIntro != null) {
            res.setDescription(contentIntro.getDescription());
        }
        // 设置分类
        return res;
    }

    @Override
    public Page<CmsCourse> pageSearchForWeb(Page page, CourseSearchParamDto courseSearchParam) {
//        Page

        if (courseSearchParam.getType() != null) {
            courseSearchParam.setTypes(null);
        }

//        if (courseSearchParam.getTypes() != null) {
//
//        }
//        else {
//            courseSearchParam.setTypes(Arrays.asList(1, 2, 3));
//        }
        courseSearchParam.setStatus(CmsCourse.ON_SHELF);
        Page<CmsCourse> res = baseMapper.pageSearch(page, courseSearchParam);
//        List<CmsCourse> records = res.getRecords();
//        if (records.isEmpty()) {
//            return res;
//        }
//        ;

        //
//        Map<Long, CmsClassify> collect = iCmsClassifyService.listByCourseIds(records.stream().map(v -> v.getId()).collect(Collectors.toSet()))
//                .stream().collect(Collectors.toMap(CmsClassify::getId, Function.identity()));
        return res;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean putaway(Operator operator, Long id) {
        CmsCourse course = this.getById(id);
        AssertUtils.notNull(course, "课程不存在");
        if (course.getStatus() == 2) {
            throw new BusinessException("该课程已经是上架状态了");
        }
        course.setStatus(2);
        course.setActualOnShelfTime(LocalDateTime.now());
        course.setModifier(operator.getId());
        course.setModifyTime(LocalDateTime.now());
        return this.updateById(course);
    }

    @Override
    public boolean soldOut(Operator operator, Long id) {
        CmsCourse course = this.getById(id);
        AssertUtils.notNull(course, "课程不存在");
        if (course.getStatus() != 2) {
            throw new BusinessException("该课程不是上架状态,不能下架");
        }
        course.setStatus(3);
        course.setModifier(operator.getId());
        course.setModifyTime(LocalDateTime.now());
        return this.updateById(course);
    }

    @Override
    public Page<CmsCourse> pageSearch(Page page, CourseSearchParamDto param) {
        return baseMapper.pageSearch(page, param);
    }

    @Override
    public boolean incrementApplyNum(Long courseId) {
        baseMapper.incrementApplyNum(courseId);
        return true;
    }

    private static Map<Integer, Integer> typeCountMap = new HashMap<>();

    static {
        typeCountMap.put(1, 8);
        typeCountMap.put(2, 6);
        typeCountMap.put(3, 10);
        typeCountMap.put(4, 5);
        typeCountMap.put(5, 8);
        typeCountMap.put(6, 5);
    }

    @Override
    public List<CmsCourse> listCourseByTypeOrderActualOnShelfTimeDesc(Integer type) {
        // 获取数量
        //  1课程（视频、直播、附件、说明） 8 , 2专栏（文章、音频、附件、说明） 6 3 每日一课（短视频） 4 公开课（仅视频或者与课程一样, 直播， 附件, 简介） 5 微课  6 训练营
        Integer count = typeCountMap.get(type);
        if (count == null) {
            return Collections.emptyList();
        }
        List<CmsCourse> res = baseMapper.listCourseByTypeOrderActualOnShelfTimeDesc(type, count);
        return res;
    }

    @Override
    public List<CmsCourse> newCourses(Integer count) {
        List<CmsCourse> res = baseMapper.newCourses(count);
        return res;
    }

    @Override
    public List<GroupCount> countByType(Operator operator) {
        List<GroupCount> res = baseMapper.countByType();
        return res;
    }
}
