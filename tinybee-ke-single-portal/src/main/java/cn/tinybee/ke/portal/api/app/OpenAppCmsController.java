package cn.tinybee.ke.portal.api.app;

import cn.tinybee.ke.biz.cms.dto.CmsCourseDto;
import cn.tinybee.ke.biz.cms.entity.CmsCourse;
import cn.tinybee.ke.biz.cms.entity.CmsPeriod;
import cn.tinybee.ke.biz.cms.service.ICmsCourseService;
import cn.tinybee.ke.biz.cms.service.ICmsPeriodService;
import cn.tinybee.ke.common.web.ApiResult;
import cn.tinybee.ke.core.base.BaseController;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author huanghao
 * @version 1.0
 * @description
 * @date 2021/1/6 16:02
 */
@RestController
@RequestMapping("/api/open/app/cms")
public class OpenAppCmsController extends BaseController {

    @Autowired
    private ICmsCourseService iCmsCourseService;

    @Autowired
    private ICmsPeriodService iCmsPeriodService;

    @GetMapping("/getRecommendCourses")
    public ApiResult<List<CmsCourse>> getRecommendCourses() {
        Page<CmsCourse> page = iCmsCourseService.page(new Page<>(1, 12));
        return ApiResult.success(page.getRecords());
    }

    @GetMapping("/getCourseDetail/{courseId}")
    public ApiResult<CmsCourseDto> getCourseDetail (@PathVariable Long courseId) {
        CmsCourseDto detailForWeb = iCmsCourseService.getDetailForWeb(this.getCurrentUserId(), courseId);
        return ApiResult.success(detailForWeb);
    }

    @GetMapping("/getPeriodsByCourseId/{courseId}")
    private ApiResult<List<CmsPeriod>> periodsByCourseId (@PathVariable Long courseId) {
        List<CmsPeriod> cmsPeriods = iCmsPeriodService.periodsByCourseId(courseId);
        // 处理学习记录
        return ApiResult.success(cmsPeriods);
    }
}
