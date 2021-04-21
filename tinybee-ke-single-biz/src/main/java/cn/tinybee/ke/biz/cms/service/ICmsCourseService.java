package cn.tinybee.ke.biz.cms.service;

import cn.tinybee.ke.biz.cms.dto.CmsCourseDto;
import cn.tinybee.ke.biz.cms.dto.CourseSearchParamDto;
import cn.tinybee.ke.biz.cms.entity.CmsCourse;
import cn.tinybee.ke.common.entity.GroupCount;
import cn.tinybee.ke.common.entity.Operator;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 内容主表 服务类
 * </p>
 *
 * @author hao.huang
 * @since 2020-04-10
 */
public interface ICmsCourseService extends IService<CmsCourse> {


    boolean saveOrUpdateContent(Operator user, CmsCourseDto content);

    CmsCourseDto get(Long id);

    boolean delete(Operator operator, Long id);

    CmsCourseDto getDetailForWeb(Long currentUserId, Long id);

    Page<CmsCourse> pageSearchForWeb(Page page, CourseSearchParamDto courseSearchParam);

    boolean putaway(Operator operator, Long id);

    boolean soldOut(Operator operator, Long id);

    Page<CmsCourse> pageSearch(Page page, CourseSearchParamDto param);

    boolean incrementApplyNum(Long courseId);

    List<CmsCourse> listCourseByTypeOrderActualOnShelfTimeDesc(Integer type);

    List<CmsCourse> newCourses(Integer count);

    List<GroupCount> countByType(Operator operator);
}
