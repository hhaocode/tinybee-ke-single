package cn.tinybee.ke.admin.system.controller;


import cn.tinybee.ke.admin.system.entity.SystemPermission;
import cn.tinybee.ke.admin.system.entity.SystemRole;
import cn.tinybee.ke.admin.system.service.SystemRoleService;
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

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 角色表 前端控制器
 * </p>
 *
 * @author hao.huang
 * @since 2020-03-29
 */
@RestController
@RequestMapping("/api/system-role")
public class SystemRoleController extends BaseController {

    @Autowired
    private SystemRoleService systemRoleService;

    @PostMapping("/save")
    @RequiresPermissions("role-manage/save")
    public ApiResult<Boolean> save(@RequestBody SystemRole role) {
        if (role.getId() == null) {
            role.setCreateTime(LocalDateTime.now());
            role.setCreator(this.getCurrentUserId());
        }
        return ApiResult.success(systemRoleService.saveOrUpdate(role));
    }

    @GetMapping("/{id}")
    @RequiresPermissions("role-manage/query")
    public ApiResult<SystemRole> get(@PathVariable Long id) {
        SystemRole role = systemRoleService.getById(id);
        if (role == null) {
            return ApiResult.noFound();
        }
        return ApiResult.success(role);
    }

    @DeleteMapping("/{id}")
    @RequiresPermissions("role-manage/delete")
    public ApiResult<Boolean> delete(@PathVariable Long id) {
        return ApiResult.success(systemRoleService.removeById(id));
    }

    @GetMapping("/page")
    @RequiresPermissions("role-manage/query")
    public ApiResult<IPage<SystemRole>> page(PageParam pageParam, KeywordQueryParam param){
        QueryWrapper<SystemRole> queryWrapper = new QueryWrapper<>();
        IPage<SystemRole> res = systemRoleService.page(pageConvert(pageParam), queryWrapper);
        return ApiResult.success(res);
    }

    @GetMapping("/resource-ids/{roleId}")
    @RequiresPermissions("role-manage/query")
    public ApiResult<List<Long>> getRoleResourceIds(@PathVariable Long roleId) {
        List<SystemPermission> roleResource = systemRoleService.getRoleResources(roleId);
        return ApiResult.success(roleResource.stream().map(v -> v.getId()).collect(Collectors.toList()));
    }

    @PutMapping("/save/role-resources")
    @RequiresPermissions("role-manage/authorize")
    public ApiResult<Boolean> saveRoleResources(@RequestBody IdIdsType<Long, Long> roleResource) {
        return ApiResult.success(systemRoleService.saveRoleResources(roleResource, getCurrentUserId()));
    }

    // 赋予 资源权限
    // 赋予 数据权限
}
