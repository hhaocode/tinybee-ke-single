package cn.tinybee.ke.admin.system.controller;

import cn.tinybee.ke.admin.system.dto.AuthParamDto;
import cn.tinybee.ke.admin.system.dto.AuthResultDto;
import cn.tinybee.ke.admin.system.dto.CacheUserDto;
import cn.tinybee.ke.admin.system.dto.SystemPermissionDto;
import cn.tinybee.ke.admin.system.entity.SystemPermission;
import cn.tinybee.ke.admin.system.entity.SystemRole;
import cn.tinybee.ke.admin.system.entity.SystemUser;
import cn.tinybee.ke.admin.system.service.AuthService;
import cn.tinybee.ke.admin.system.service.SystemPermissionService;
import cn.tinybee.ke.admin.system.service.SystemRoleService;
import cn.tinybee.ke.admin.system.service.SystemUserService;
import cn.tinybee.ke.common.exception.LoginFailedException;
import cn.tinybee.ke.common.web.ApiResult;
import cn.tinybee.ke.core.base.BaseController;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author hao.huang
 * @description
 * @date 2020/3/26
 */
@Slf4j
@Api(tags = "认证")
@RestController
@RequestMapping("/api/auth")
public class AuthController extends BaseController {

    @Autowired
    private AuthService authService;

    @Autowired
    private SystemUserService systemUserService;

    @Autowired
    private SystemRoleService systemRoleService;

    @ApiOperation("登录")
    @PostMapping("/login")
    public ApiResult<AuthResultDto> login(@RequestBody @Validated AuthParamDto param,
                                          HttpServletRequest request) {
        ApiResult<AuthResultDto> res = new ApiResult<>();
        if (StringUtils.isBlank(param.getPassword()) || StringUtils.isBlank(param.getUsername())) {
            res.setCode(1);
            res.setMessage("用户名与密码不能为空");
            return res;
        }
        try {
            AuthResultDto resultDto = authService.login(param.getUsername(), param.getPassword(), request);
            res.setData(resultDto);
        } catch (LoginFailedException e) {
            log.info("登录失败：{}", e.getCause());
            res.setCode(1);
            res.setMessage(e.getMessage());
        }
        return res;
    }

    @GetMapping("/administrator")
    public ApiResult<CacheUserDto> administrator() {
        CacheUserDto currentUser = getCurrentUser();
        SystemUser user = systemUserService.getById(currentUser.getId());
        // 获取菜单
        CacheUserDto cacheUserDto = authService.wrapperUser2CacheUser(user, currentUser.getToken(), currentUser.getLoginIp());
        return ApiResult.success(cacheUserDto);
    }

    @GetMapping("/logout")
    public ApiResult<Boolean> logout(HttpServletRequest request) {
        CacheUserDto currentUser = getCurrentUser();
        if (currentUser == null) {
            return ApiResult.success(true);
        }
        return ApiResult.success(authService.logout(currentUser.getUsername(), request));
    }

    @GetMapping("/allMenuTree")
    public  ApiResult<List<SystemPermissionDto>> allMenuTree() {
        return ApiResult.success(systemUserService.getUserPermissionTree(getCurrentUser(), null, null));
    }

    @GetMapping("/menuTree")
    public  ApiResult<List<SystemPermissionDto>> menus() {
        return ApiResult.success(systemUserService.getUserPermissionTree(getCurrentUser(), Arrays.asList(1, 2), Boolean.TRUE));
    }

    @GetMapping("/menuInfo")
    public  ApiResult<Map<String, List<SystemPermissionDto>>> menusInfo() {
        Map<String, List<SystemPermissionDto>> res = new HashMap<>();
        res.put("menuTree", systemUserService.getUserPermissionTree(getCurrentUser(), Arrays.asList(1), Boolean.TRUE));
        res.put("allMenuTree", systemUserService.getUserPermissionTree(getCurrentUser(), null, null));
        return ApiResult.success(res);
    }

    @GetMapping("/resource/tree")
    public ApiResult<List<SystemPermissionDto>> resourceTree() {
        return ApiResult.success(systemUserService.getUserPermissionTree(getCurrentUser(), null, Boolean.TRUE));
    }

    @GetMapping("/role/list")
    public ApiResult<List<SystemRole>> roleList() {
        CacheUserDto currentUser = this.getCurrentUser();
        if (currentUser.getSuperAdmin()) {
            //
            QueryWrapper<SystemRole> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("available", true);
            queryWrapper.orderByDesc("create_time");
            return ApiResult.success(systemRoleService.list(queryWrapper));
        }
        return ApiResult.success(systemUserService.getUserRoles(this.getCurrentUserId()));
    }

    @GetMapping("/resource/list")
    public ApiResult<List<SystemPermission>> getUserResources() {
        return ApiResult.success(systemUserService.getUserPermissions(this.getCurrentUser(), null));
    }
}
