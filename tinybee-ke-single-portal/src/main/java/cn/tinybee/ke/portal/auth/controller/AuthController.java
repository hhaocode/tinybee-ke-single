package cn.tinybee.ke.portal.auth.controller;

import cn.tinybee.ke.common.web.ApiResult;
import cn.tinybee.ke.core.base.BaseController;
import cn.tinybee.ke.portal.core.enums.AuthCodeChannel;
import cn.tinybee.ke.portal.core.enums.AuthCodeType;
import cn.tinybee.ke.portal.core.security.ArcusCacheUser;
import cn.tinybee.ke.portal.auth.service.AuthService;
import cn.tinybee.ke.portal.auth.dto.MemberRegisterDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author hao.huang
 * @description
 * @date 2020/3/29
 */
@Api(tags = "AuthController", description = "认证控制器")
@RestController
@RequestMapping("/api/auth")
public class AuthController extends BaseController {

    @Autowired
    private AuthService authService;

    /**
     *
     * @param registerDto
     * @return
     */
    @ApiOperation("注册")
    @PostMapping("/register")
    public ApiResult<Boolean> register(@RequestBody MemberRegisterDto registerDto) {
        return ApiResult.success(authService.register(registerDto));
    }

    @ApiOperation("获取缓存中的用户信息")
    @GetMapping("/cache-user")
    public ApiResult<ArcusCacheUser> cacheUser() {
        ArcusCacheUser currentUser = this.getCurrentUser();
        if (currentUser != null) {
            return ApiResult.success(currentUser);
        }
        return ApiResult.frontSkipAuth();
    }

    @ApiOperation("发送验证码")
    @GetMapping("/send/{channel}/{type}/auth-code/{target}")
    public ApiResult<Boolean> sendMobileAuthCode(@PathVariable AuthCodeChannel channel, @PathVariable String target, @PathVariable AuthCodeType type) {
        authService.sendMobileAuthCode(channel, target, type);
        return ApiResult.success();
    }

}
