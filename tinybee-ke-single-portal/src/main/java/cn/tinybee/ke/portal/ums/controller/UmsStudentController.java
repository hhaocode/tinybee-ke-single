package cn.tinybee.ke.portal.ums.controller;


import cn.tinybee.ke.biz.ums.dto.UserPasswordDto;
import cn.tinybee.ke.biz.ums.entity.UmsStudent;
import cn.tinybee.ke.biz.ums.service.IUmsStudentService;
import cn.tinybee.ke.common.web.ApiResult;
import cn.tinybee.ke.core.base.BaseController;
import cn.tinybee.ke.portal.auth.dto.MobileAuthParamDto;
import cn.tinybee.ke.portal.auth.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author hao.huang
 * @since 2020-03-29
 */
@RestController
@RequestMapping("/api/ums/user")
public class UmsStudentController extends BaseController {

    // 会员信息
    @Autowired
    private IUmsStudentService iUmsStudentService;

    @Autowired
    private AuthService authService;

    @GetMapping("/get")
    public ApiResult<UmsStudent> get() {
        return ApiResult.success(iUmsStudentService.getById(this.getCurrentUserId()));
    }

    @PutMapping("/modifyEmail")
    public ApiResult<Boolean> modifyEmail (@RequestParam String email) {
        return ApiResult.success(authService.modifyEmail(email, this.getCurrentUserId()));
    }

    @PutMapping("/modifyPassword")
    public ApiResult<Boolean> modifyPassword (@RequestBody @Validated UserPasswordDto userPasswordDto) {
        return ApiResult.success(authService.modifyPassword(userPasswordDto, this.getCurrentUserId()));
    }

    @PutMapping("/mobileAuthParamDto")
    public ApiResult<Boolean> mobileAuthParamDto (@RequestBody MobileAuthParamDto mobileAuthParamDto) {
        return ApiResult.success(authService.modifyMobile(mobileAuthParamDto, this.getCurrentUserId()));
    }


    @PutMapping("/logoff")
    public ApiResult<Boolean> logoff () {
        return ApiResult.success(authService.logoff(this.getCurrentUserId()));
    }


}
