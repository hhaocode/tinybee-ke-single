package cn.tinybee.ke.portal.ums.controller;


import cn.tinybee.ke.biz.ums.dto.UserCourseDetailDto;
import cn.tinybee.ke.biz.ums.entity.UmsStudentCourse;
import cn.tinybee.ke.biz.ums.service.IUmsStudentCourseService;
import cn.tinybee.ke.common.entity.PageParam;
import cn.tinybee.ke.common.web.ApiResult;
import cn.tinybee.ke.core.base.BaseController;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 会员拥有内容表 前端控制器
 * </p>
 *
 * @author hao.huang
 * @since 2020-05-02
 */
@Api("会员拥有内容")
@RestController
@RequestMapping("/api/ums/user-course")
public class UmsStudentCourseController extends BaseController {

    @Autowired
    private IUmsStudentCourseService IUmsStudentCourseService;

    @PostMapping("/join/{contentId}")
    public ApiResult<Boolean> join(@PathVariable Long contentId) {
        return ApiResult.success(IUmsStudentCourseService.join(contentId, getCurrentUserId(), false));
    }


    @GetMapping("/info/{contentId}")
    public ApiResult<UmsStudentCourse> memberContent(@PathVariable Long contentId) {
        return ApiResult.success(IUmsStudentCourseService.memberContent(contentId, this.getCurrentUserId()));
    }

    @GetMapping("/page")
    public ApiResult<Page<UserCourseDetailDto>> memberCoursePage (PageParam pageParam) {
        return ApiResult.success(IUmsStudentCourseService.memberCoursePage(this.pageConvert(pageParam), this.getCurrentUserId()));
    }
}
