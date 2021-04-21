package cn.tinybee.ke.biz.cms.service.impl;

import cn.tinybee.ke.biz.cms.dto.CmsPeriodDto;
import cn.tinybee.ke.biz.cms.entity.*;
import cn.tinybee.ke.biz.cms.mapper.CmsPeriodMapper;
import cn.tinybee.ke.biz.cms.service.*;
import cn.tinybee.ke.biz.material.dto.MaterialDto;
import cn.tinybee.ke.biz.material.entity.MaterialArticle;
import cn.tinybee.ke.biz.material.entity.MaterialFile;
import cn.tinybee.ke.biz.material.service.IMaterialArticleService;
import cn.tinybee.ke.biz.material.service.IMaterialFileService;
import cn.tinybee.ke.common.entity.Operator;
import cn.tinybee.ke.common.exception.BusinessException;
import cn.tinybee.ke.common.util.AssertUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 内容文章表 服务实现类
 * </p>
 *
 * @author hao.huang
 * @since 2020-04-10
 */
@Service
public class CmsPeriodServiceImpl extends ServiceImpl<CmsPeriodMapper, CmsPeriod> implements ICmsPeriodService {

    @Autowired
    private Mapper mapper;

    @Autowired
    private ICmsPeriodVodService iCmsPeriodVodService;

    @Autowired
    @Lazy
    private ICmsChapterService iCmsChapterService;

    @Autowired
    private ICmsCourseService iCmsCourseService;

    @Autowired
    private ICmsLiveService cmsLiveService;

    @Autowired
    private ICmsPeriodFileService cmsPeriodFileService;

    @Autowired
    private IMaterialFileService materialFileService;

    @Autowired
    private IMaterialArticleService materialArticleService;

    @Override
    public Map<Long, List<CmsPeriodDto>> mapItemByContentId(Long contentId) {
        QueryWrapper<CmsPeriod> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_id", contentId);
        Map<Long, List<CmsPeriodDto>> res = new HashMap<>();
        this.list(queryWrapper).stream().forEach(v -> {
            List<CmsPeriodDto> cmsContentItems = res.get(v.getChapterId());
            if (cmsContentItems == null) {
                cmsContentItems = new ArrayList<>();
                res.put(v.getChapterId(), cmsContentItems);
            }
            cmsContentItems.add(mapper.map(v, CmsPeriodDto.class));
        });
        return res;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public CmsPeriod saveOrModify(CmsPeriod cmsPeriod, Long currentUserId) {
        LocalDateTime now = LocalDateTime.now();
        if (cmsPeriod.getId() == null) {
            cmsPeriod.setCreator(currentUserId);
            cmsPeriod.setCreateTime(now);
        }
        CmsCourse cmsCourse = iCmsCourseService.getById(cmsPeriod.getCourseId());
        AssertUtils.notNull(cmsCourse, "未知课程");

        // 任何课程都可以 设置 视频 音频 文章 直播 文件
        // 这几种必选一项 不能为空

//        switch (cmsPeriod.getResType()) {
//
//            default:
//                throw new BusinessException("课程信息不完整，请先完善课程信息");
//        }
        AssertUtils.notNull(cmsPeriod.getResourceType(), "请选择资源类型");
        AssertUtils.notNull(cmsPeriod.getResourceId(), "请选择资源内容");
        this.saveOrUpdate(cmsPeriod);
        // 处理 files
        List<MaterialFile> files = cmsPeriod.getFiles();
        cmsPeriodFileService.handleSave(cmsPeriod, files);
        if (cmsPeriod.getHasLive()) {
            cmsLiveService.modifyPeriodLive(currentUserId, cmsPeriod);
        } else {
        }
        return this.getPeriodById(cmsPeriod.getId());
    }

    @Override
    public CmsPeriod getPeriodById(Long id) {
        CmsPeriod cmsPeriod = baseMapper.getDetailById(id);

        AssertUtils.notNull(cmsPeriod, "无效的课时");
        // 资源类型  3 视频  5 图文+音频6直播
        Integer resType = cmsPeriod.getResourceType();
        switch (resType) {
            case 3: // 视频
                MaterialFile video = materialFileService.getById(cmsPeriod.getResourceId());
                if (video != null) {
                    cmsPeriod.setResource(MaterialDto.fromMaterialFile(video));
                }
                break;
            case 5: // 图文+音频
                MaterialArticle article = materialArticleService.getById(cmsPeriod.getResourceId());
                if (article != null) {
                    cmsPeriod.setResource(MaterialDto.fromMaterialArticle(article));
                }
                if (cmsPeriod.getAdditionResourceId() != null) {
                    MaterialFile audio = materialFileService.getById(cmsPeriod.getAdditionResourceId());
                    if (audio != null) {
                        cmsPeriod.setAdditionResource(MaterialDto.fromMaterialFile(audio));
                    }
                }
                break;
            case 6: // 直播
                CmsLive live = cmsLiveService.getPeriodLiveByPeriodId(id);
                if (live != null) {
                    cmsPeriod.setLiveStartTime(live.getStartTime());
                    cmsPeriod.setLiveEndTime(live.getEndTime());
                    cmsPeriod.setResource(MaterialDto.formCmsLive(live));
                }
                break;
        }

        List<MaterialFile> materialFiles = cmsPeriodFileService.listFileByPeriodId(id);
        cmsPeriod.setFiles(materialFiles);
        cmsPeriod.setFileIds(materialFiles.stream().map(v -> v.getId()).collect(Collectors.toList()));
        return cmsPeriod;
    }

    @Override
    public List<CmsPeriod> periodsByCourseId(Long courseId) {
        // 获取
        QueryWrapper<CmsPeriod> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_id", courseId);
        List<CmsPeriod> list = this.list(queryWrapper);
//        if (list.isEmpty()) {
//            return Collections.EMPTY_LIST;
//        }

        // 查询章节
        List<CmsChapter> cmsChapters = iCmsChapterService.chaptersByCourseId(courseId);
        // 将章节转为
        List<CmsPeriod> collect = cmsChapters.stream().map(v -> {
            CmsPeriod cmsPeriod = new CmsPeriod();
            cmsPeriod.setId(v.getId());
            cmsPeriod.setCourseId(courseId);
            cmsPeriod.setChapterId(0L);
            cmsPeriod.setTitle(v.getTitle());
            cmsPeriod.setType(CmsPeriod.CmsPeriodType.CHAPTER);
            cmsPeriod.setSort(v.getSort());
            return cmsPeriod;
        }).collect(Collectors.toList());
        // 所有
//        list.addAll(collect);
        //
        Map<Long, List<CmsPeriod>> chapterIdList = list.stream().collect(Collectors.groupingBy(CmsPeriod::getChapterId));

        // 取出所有为0的
        list.forEach(v -> {
            v.setType(CmsPeriod.CmsPeriodType.PERIOD);
        });
        List<CmsPeriod> cmsPeriods = list.stream().filter(v -> v.getChapterId() == 0).collect(Collectors.toList());
        if (!cmsPeriods.isEmpty()) {
            collect.addAll(cmsPeriods);
        }
        Collections.sort(collect);
        collect.forEach(v -> {
            List<CmsPeriod> children = chapterIdList.get(v.getId());
            if (children == null) {
                children = Collections.emptyList();
            }
            v.setChildren(children);
        });

        return collect;
    }

    @Transactional
    @Override
    public boolean doUpdate(Operator user, Long id) {
        CmsPeriod cmsPeriod = this.getById(id);
        AssertUtils.notNull(cmsPeriod, "课时不存在");
        if (cmsPeriod.getStatus() == null || cmsPeriod.getStatus() != 1) {
            throw new BusinessException("课时已经不是待更新状态");
        }
        cmsPeriod.setStatus(2);
        return this.updateById(cmsPeriod);
    }

}
