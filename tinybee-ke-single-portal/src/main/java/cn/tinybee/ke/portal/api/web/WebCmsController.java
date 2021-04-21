package cn.tinybee.ke.portal.api.web;

import cn.tinybee.ke.biz.cms.dto.CmsCourseDto;
import cn.tinybee.ke.biz.cms.entity.CmsPeriod;
import cn.tinybee.ke.biz.cms.service.ICmsCourseService;
import cn.tinybee.ke.biz.cms.service.ICmsPeriodService;
import cn.tinybee.ke.biz.ums.entity.UmsStudentCourse;
import cn.tinybee.ke.biz.ums.entity.UmsStudentCourseLog;
import cn.tinybee.ke.biz.ums.service.IUmsStudentCourseLogService;
import cn.tinybee.ke.biz.ums.service.IUmsStudentCourseService;
import cn.tinybee.ke.common.entity.Operator;
import cn.tinybee.ke.common.util.AssertUtils;
import cn.tinybee.ke.common.web.ApiResult;
import cn.tinybee.ke.core.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author huanghao
 * @version 1.0
 * @description
 * @date 2020/12/25 17:56
 */
@RestController
@RequestMapping("/api/web/cms")
public class WebCmsController extends BaseController {

    @Autowired
    private ICmsPeriodService iCmsPeriodService;

    @Autowired
    private IUmsStudentCourseService iUmsStudentCourseService;

    @Autowired
    private ICmsCourseService iCmsCourseService;

    @Autowired
    private IUmsStudentCourseLogService iUmsStudentCourseLogService;

    /**
     * 根据课程ID获取列表
     * @param courseId
     * @return
     */
    @GetMapping("/periodsByCourseId/{courseId}")
    private ApiResult<List<CmsPeriod>> periodsByCourseId (@PathVariable Long courseId) {
        UmsStudentCourse umsStudentCourse = iUmsStudentCourseService.memberContent(courseId, this.getCurrentUserId());
        AssertUtils.notNull(umsStudentCourse, "您未购买此课程，请先购买");
        List<CmsPeriod> cmsPeriods = iCmsPeriodService.periodsByCourseId(courseId);
        // TODO 处理学习记录
        return ApiResult.success(cmsPeriods);
    }

    /**
     * 获取课程ID
     * @param id
     * @return
     */
    @GetMapping("/detail/{id}")
    public ApiResult<CmsCourseDto> detail(@PathVariable Long id) {
        CmsCourseDto cmsCourseDto = iCmsCourseService.getDetailForWeb(this.getCurrentUserId(), id);
        UmsStudentCourse umsStudentCourse = iUmsStudentCourseService.memberContent(id, this.getCurrentUserId());
        cmsCourseDto.setUmsStudentCourse(umsStudentCourse);
        cmsCourseDto.setPurchased(umsStudentCourse != null);
        return ApiResult.success(cmsCourseDto);
    }

    /**
     * 获取课时
     * @param periodId
     * @return
     */
    @GetMapping("/period/{periodId}")
    public ApiResult<CmsPeriod> periodById (@PathVariable Long periodId) {
        CmsPeriod cmsPeriod = iCmsPeriodService.getPeriodById(periodId);
        // 获取学习日志
        Operator user = this.getOptUser();
        UmsStudentCourseLog courseLog = iUmsStudentCourseLogService.getLogByPeriodIdAndUserId (cmsPeriod.getCourseId() ,getCurrentUserId(), periodId);
        cmsPeriod.setLog(courseLog);
        return ApiResult.success(cmsPeriod);
    }

    @PutMapping("/changePeriodLog/{courseId}/{period}")
    public ApiResult<Boolean> changePeriodLog (@PathVariable Long courseId, @PathVariable Long period, @RequestParam Long time) {
        return ApiResult.success(iUmsStudentCourseLogService.changeLog(courseId, period, time, this.getCurrentUserId()));
    }
}
