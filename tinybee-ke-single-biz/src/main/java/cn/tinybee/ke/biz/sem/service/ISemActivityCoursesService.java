package cn.tinybee.ke.biz.sem.service;

import cn.tinybee.ke.biz.sem.entity.SemActivityCourses;
import cn.tinybee.ke.common.entity.Operator;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Collection;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hao.huang
 * @since 2021-01-19
 */
public interface ISemActivityCoursesService extends IService<SemActivityCourses> {

    boolean changeActivityCourses(Operator operator, Long activityId, List<SemActivityCourses> courses);

    List<SemActivityCourses> listCourseByActivityId(Long activityId);


    List<SemActivityCourses> listCourseByActivityIds(Collection<Long> activityIds);
}
