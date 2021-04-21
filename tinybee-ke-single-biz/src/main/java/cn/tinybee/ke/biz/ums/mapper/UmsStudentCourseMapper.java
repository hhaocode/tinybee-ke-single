package cn.tinybee.ke.biz.ums.mapper;

import cn.tinybee.ke.biz.ums.dto.UserCourseDetailDto;
import cn.tinybee.ke.biz.ums.entity.UmsStudentCourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 会员拥有内容表 Mapper 接口
 * </p>
 *
 * @author hao.huang
 * @since 2020-05-02
 */
public interface UmsStudentCourseMapper extends BaseMapper<UmsStudentCourse> {

    Page<UserCourseDetailDto> memberCoursePage(Page page, @Param("userId") Long userId);
}
