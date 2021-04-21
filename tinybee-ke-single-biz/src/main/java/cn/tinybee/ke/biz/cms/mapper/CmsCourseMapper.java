package cn.tinybee.ke.biz.cms.mapper;

import cn.tinybee.ke.biz.cms.dto.CmsCourseDto;
import cn.tinybee.ke.biz.cms.dto.CourseSearchParamDto;
import cn.tinybee.ke.biz.cms.entity.CmsCourse;
import cn.tinybee.ke.common.entity.GroupCount;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 内容主表 Mapper 接口
 * </p>
 *
 * @author hao.huang
 * @since 2020-04-10
 */
public interface CmsCourseMapper extends BaseMapper<CmsCourse> {

    CmsCourse getDetailById ( @Param("id") Long id);

    Page<CmsCourse> pageSearch(Page page, @Param("courseSearchParam") CourseSearchParamDto courseSearchParam);

    int incrementApplyNum (@Param("courseId") Long courseId);

    List<CmsCourse> listCourseByTypeOrderActualOnShelfTimeDesc(@Param("type") Integer type,@Param("count") Integer count);

    List<CmsCourse> newCourses(@Param("count") Integer count);

    List<GroupCount> countByType();

}
