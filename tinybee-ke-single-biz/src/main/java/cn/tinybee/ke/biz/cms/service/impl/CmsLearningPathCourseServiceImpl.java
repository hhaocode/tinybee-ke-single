package cn.tinybee.ke.biz.cms.service.impl;

import cn.tinybee.ke.biz.cms.entity.CmsLearningPathCourse;
import cn.tinybee.ke.biz.cms.mapper.CmsLearningPathCourseMapper;
import cn.tinybee.ke.biz.cms.service.ICmsLearningPathCourseService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hao.huang
 * @since 2021-03-14
 */
@Service
public class CmsLearningPathCourseServiceImpl extends ServiceImpl<CmsLearningPathCourseMapper, CmsLearningPathCourse> implements ICmsLearningPathCourseService {

    @Override
    public List<CmsLearningPathCourse> listPathCourseByPathId(Long pathId) {
        QueryWrapper<CmsLearningPathCourse> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("path_id", pathId);
        queryWrapper.orderByAsc("sort");
        return this.list(queryWrapper);
    }

    @Override
    public int countCourseByPathId(Long pathId) {
        QueryWrapper<CmsLearningPathCourse> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("path_id", pathId);
        return this.count(queryWrapper);
    }
}
