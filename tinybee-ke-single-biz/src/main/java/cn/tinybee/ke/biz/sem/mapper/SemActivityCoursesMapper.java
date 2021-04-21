package cn.tinybee.ke.biz.sem.mapper;

import cn.tinybee.ke.biz.sem.entity.SemActivityCourses;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author hao.huang
 * @since 2021-01-19
 */
public interface SemActivityCoursesMapper extends BaseMapper<SemActivityCourses> {

    List<SemActivityCourses> listCourseByActivityId(@Param("activityId") Long activityId);

    List<SemActivityCourses> listCourseByActivityIds(@Param("activityIds") Collection<Long> activityIds);
}
