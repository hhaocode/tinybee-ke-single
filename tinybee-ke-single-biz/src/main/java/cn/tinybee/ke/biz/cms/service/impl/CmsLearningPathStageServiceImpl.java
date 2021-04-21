package cn.tinybee.ke.biz.cms.service.impl;

import cn.tinybee.ke.biz.cms.entity.CmsCourse;
import cn.tinybee.ke.biz.cms.entity.CmsLearningPathCourse;
import cn.tinybee.ke.biz.cms.entity.CmsLearningPathStage;
import cn.tinybee.ke.biz.cms.mapper.CmsLearningPathStageMapper;
import cn.tinybee.ke.biz.cms.service.ICmsCourseService;
import cn.tinybee.ke.biz.cms.service.ICmsLearningPathCourseService;
import cn.tinybee.ke.biz.cms.service.ICmsLearningPathStageService;
import cn.tinybee.ke.common.entity.Operator;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * <p>
 * 学习路径阶段 服务实现类
 * </p>
 *
 * @author hao.huang
 * @since 2021-03-14
 */
@Service
public class CmsLearningPathStageServiceImpl extends ServiceImpl<CmsLearningPathStageMapper, CmsLearningPathStage> implements ICmsLearningPathStageService {

    private ICmsLearningPathCourseService cmsLearningPathCourseService;

    private ICmsCourseService cmsCourseService;

    @Autowired
    public void setCmsLearningPathCourseService(ICmsLearningPathCourseService cmsLearningPathCourseService) {
        this.cmsLearningPathCourseService = cmsLearningPathCourseService;
    }

    @Autowired
    public void setCmsCourseService(ICmsCourseService cmsCourseService) {
        this.cmsCourseService = cmsCourseService;
    }

    @Override
    public List<CmsLearningPathStage> listStageByPathId(Long pathId) {
        QueryWrapper<CmsLearningPathStage> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("path_id", pathId);
        queryWrapper.orderByAsc("sort");
        List<CmsLearningPathStage> list = this.list(queryWrapper);
        if (list.isEmpty()) {
            return list;
        }


        List<CmsLearningPathCourse> cmsLearningPathCourses = cmsLearningPathCourseService.listPathCourseByPathId(pathId);
        if (cmsLearningPathCourses.isEmpty()) {
            return list;
        }
        Set<Long> courseIds = cmsLearningPathCourses.stream().map(v -> v.getCourseId()).collect(Collectors.toSet());
        Map<Long, CmsCourse> longCmsCourseMap = cmsCourseService.listByIds(courseIds).stream().collect(Collectors.toMap(CmsCourse::getId, Function.identity()));
        cmsLearningPathCourses.forEach(v -> {
            v.setCmsCourse(longCmsCourseMap.get(v.getCourseId()));
        });


        Map<Long, List<CmsLearningPathCourse>> collect = cmsLearningPathCourses.stream().collect(Collectors.groupingBy(CmsLearningPathCourse::getPathStageId));
        list.forEach(v -> {
            v.setPathCourses(collect.get(v.getId()));
        });
        return list;
    }

    @Override
    public int countStageByPathId(Long pathId) {
        QueryWrapper<CmsLearningPathStage> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("path_id", pathId);
        return this.count(queryWrapper);
    }

    @Override
    public boolean saveOrModify(Operator operator, CmsLearningPathStage param) {
        if (param.getId() == null) {
            // 创建
        } else {
            // 修改
        }
        this.saveOrUpdate(param);
        return true;
    }

    @Override
    public boolean deleteById(Operator operator, Long id) {
        return this.removeById(id);
    }
}
