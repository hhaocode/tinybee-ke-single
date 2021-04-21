package cn.tinybee.ke.admin.system.controller;

import cn.tinybee.ke.admin.system.dto.CacheUserDto;
import cn.tinybee.ke.admin.system.dto.UserPermissionParamDto;
import cn.tinybee.ke.admin.system.entity.SystemPermission;
import cn.tinybee.ke.admin.system.entity.SystemUser;
import cn.tinybee.ke.admin.system.service.SystemUserService;
import cn.tinybee.ke.common.entity.IdIdsType;
import cn.tinybee.ke.common.entity.KeywordQueryParam;
import cn.tinybee.ke.common.entity.PageParam;
import cn.tinybee.ke.common.web.ApiResult;
import cn.tinybee.ke.core.base.BaseController;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author hao.huang
 * @description
 * @date 2020/3/31
 */
@RestController
@RequestMapping("/api/system-user")
public class SystemUserController extends BaseController {

    @Autowired
    private SystemUserService systemUserService;

    @RequiresPermissions("system-user-manage/save")
    @GetMapping("/create/{empId}")
    public ApiResult<Boolean> createUser(@PathVariable Long empId) {
        return ApiResult.success(systemUserService.createUser(empId, getCurrentUserId()));
    }

    @GetMapping("/page")
    @RequiresPermissions("system-user-manage/query")
    public ApiResult<IPage<SystemUser>> page(PageParam pageParam, KeywordQueryParam param) {
        QueryWrapper<SystemUser> queryWrapper = new QueryWrapper<>();
        IPage<SystemUser> res = systemUserService.page(pageConvert(pageParam), queryWrapper);
        return ApiResult.success(res);
    }

    @GetMapping("/role-ids/{userId}")
    @RequiresPermissions("system-user-manage/query")
    public ApiResult<List<Long>> getUserRoleIds(@PathVariable Long userId) {
        return ApiResult.success(systemUserService.getUserRoles(userId).stream().map(v -> v.getId()).collect(Collectors.toList()));
    }

    @GetMapping("/permission-ids/{userId}")
    @RequiresPermissions("system-user-manage/query")
    public ApiResult<List<Long>> getUserPermissionIds(@PathVariable Long userId) {
        return ApiResult.success(systemUserService.getPermissionIds(userId).stream().map(v -> v.getPermissionId()).collect(Collectors.toList()));
    }

    @PutMapping("/save/user-roles")
    @RequiresPermissions("system-user-manage/authorize")
    public ApiResult<Boolean> saveUserRoles(@RequestBody UserPermissionParamDto userPermissionParam) {
        return ApiResult.success(systemUserService.saveUserRoles(userPermissionParam, getCurrentUserId()));
    }

    @GetMapping("/resource_ids/{userId}")
    @RequiresPermissions("system-user-manage/query")
    public ApiResult<List<SystemPermission>> getUserResoucesIds(@PathVariable Long userId) {
        CacheUserDto userDto = new CacheUserDto();
        userDto.setId(userId);
        userDto.setSuperAdmin(false);
        return ApiResult.success(systemUserService.getUserPermissions(userDto, null));
    }



    
}
