package cn.tinybee.ke.portal.api.web;

import cn.tinybee.ke.biz.cms.entity.CmsPeriod;
import cn.tinybee.ke.biz.cms.service.ICmsPeriodService;
import cn.tinybee.ke.biz.cms.dto.CourseSearchParamDto;
import cn.tinybee.ke.biz.cms.dto.CmsChapterPeriodsDto;
import cn.tinybee.ke.biz.cms.dto.CmsCourseDto;
import cn.tinybee.ke.biz.cms.entity.CmsCourse;
import cn.tinybee.ke.biz.cms.service.ICmsChapterService;
import cn.tinybee.ke.biz.cms.service.ICmsCourseService;
import cn.tinybee.ke.biz.ums.entity.UmsStudentCourse;
import cn.tinybee.ke.biz.ums.service.IUmsStudentCourseService;
import cn.tinybee.ke.common.entity.PageParam;
import cn.tinybee.ke.common.web.ApiResult;
import cn.tinybee.ke.core.base.BaseController;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * @Classname HomeController
 * @Description TODO
 * @Date 2020/6/7 20:38
 * @Created by hao.huang
 */
@Api("web端cms开放api")
@RestController
@RequestMapping("/api/open/web/cms")
public class WebCmsOpenController extends BaseController {

    @Autowired
    private ICmsCourseService iCmsCourseService;

    @Autowired
    private ICmsChapterService iCmsChapterService;

    @Autowired
    private IUmsStudentCourseService iUmsStudentCourseService;

    @Autowired
    private ICmsPeriodService iCmsPeriodService;

    @Autowired
    private Mapper mapper;

    @GetMapping("/course/page")
    public ApiResult<Page> page(PageParam pageParam, CourseSearchParamDto param) {
        if (param.getType() == null) {
            param.setTypes(Arrays.asList(1, 2));
        }
        Page<CmsCourse> res = iCmsCourseService.pageSearchForWeb(pageConvert(pageParam), param);
        return ApiResult.success(res);
    }

    @GetMapping("/detail/{id}")
    public ApiResult<CmsCourseDto> detail(@PathVariable Long id) {
        CmsCourseDto cmsCourseDto = iCmsCourseService.getDetailForWeb(this.getCurrentUserId(), id);
        if (this.getCurrentUserId() != null) {
            UmsStudentCourse umsStudentCourse = iUmsStudentCourseService.memberContent(id, this.getCurrentUserId());
            cmsCourseDto.setPurchased(umsStudentCourse != null);
        }
        return ApiResult.success(cmsCourseDto);
    }


    @GetMapping("/chapters/{contentId}")
    public ApiResult<List<CmsChapterPeriodsDto>> listChapterContentItemByContentId(@PathVariable Long contentId, @RequestParam(required = false) Boolean requireVod) {
        return ApiResult.success(iCmsChapterService.listChapterContentItemByContentId(contentId, requireVod));
    }

    @GetMapping("/periodsByCourseId/{courseId}")
    private ApiResult<List<CmsPeriod>> periodsByCourseId (@PathVariable Long courseId) {
        List<CmsPeriod> cmsPeriods = iCmsPeriodService.periodsByCourseId(courseId);
        // 处理学习记录
        return ApiResult.success(cmsPeriods);
    }
}
