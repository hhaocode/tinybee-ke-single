package cn.tinybee.ke.biz.sem.service.impl;

import cn.tinybee.ke.biz.sem.entity.SemActivityCourses;
import cn.tinybee.ke.biz.sem.mapper.SemActivityCoursesMapper;
import cn.tinybee.ke.biz.sem.service.ISemActivityCoursesService;
import cn.tinybee.ke.common.entity.Operator;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hao.huang
 * @since 2021-01-19
 */
@Service
public class SemActivityCoursesServiceImpl extends ServiceImpl<SemActivityCoursesMapper, SemActivityCourses> implements ISemActivityCoursesService {

    @Override
    public boolean changeActivityCourses(Operator operator, Long activityId, List<SemActivityCourses> courses) {
        QueryWrapper<SemActivityCourses> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("activity_id", activityId);
        Map<Long, SemActivityCourses> dbRelation = this.list(queryWrapper).stream().collect(Collectors.toMap(SemActivityCourses::getCourseId, Function.identity()));
        for (SemActivityCourses course : courses) {
            SemActivityCourses semActivityCourses = dbRelation.get(course.getCourseId());
            if (semActivityCourses != null) {
                // 数据库中存在
                semActivityCourses.setDelete(false);
                semActivityCourses.setActivityPrice(course.getActivityPrice());
                this.updateById(semActivityCourses);
                // 删除
                dbRelation.remove(course.getCourseId());
            } else {
                // 不存在
                semActivityCourses = new SemActivityCourses();
                semActivityCourses.setDelete(false);
                semActivityCourses.setActivityId(activityId);
                semActivityCourses.setCourseId(course.getCourseId());
                semActivityCourses.setActivityPrice(course.getActivityPrice());
                this.save(semActivityCourses);
            }
        }
        Collection<SemActivityCourses> values = dbRelation.values();
        if (!values.isEmpty()) {
            for (SemActivityCourses value : values) {
                value.setDelete(true);
            }
            this.updateBatchById(values);
        }
        return true;
    }

    @Override
    public List<SemActivityCourses> listCourseByActivityId(Long activityId) {
        List<SemActivityCourses> courses = baseMapper.listCourseByActivityId(activityId);
        return courses;
    }

    @Override
    public List<SemActivityCourses> listCourseByActivityIds(Collection<Long> activityIds) {
        return baseMapper.listCourseByActivityIds(activityIds);
    }
}
