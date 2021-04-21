package cn.tinybee.ke.admin.system.controller;

import cn.tinybee.ke.admin.system.dto.SystemPermissionDto;
import cn.tinybee.ke.admin.system.entity.SystemPermission;
import cn.tinybee.ke.admin.system.service.SystemPermissionService;
import cn.tinybee.ke.common.entity.KeywordQueryParam;
import cn.tinybee.ke.common.web.ApiResult;
import cn.tinybee.ke.core.base.BaseController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author hao.huang
 * @description
 * @date 2020/3/27
 */
@RestController
@RequestMapping("/api/system-resource")
public class SystemPermissionController extends BaseController {

    @Autowired
    private SystemPermissionService systemPermissionService;

    @PostMapping("/save")
    @RequiresPermissions("permission-manage/save")
    public ApiResult<Boolean> save(@RequestBody @Validated SystemPermission resource) {
        if (resource.getAvailable() == null) {
            resource.setAvailable(true);
        }
        if(resource.getPid() == null) {
            resource.setPid(0L);
        }
        return ApiResult.success(systemPermissionService.saveOrUpdate(resource));
    }

    @DeleteMapping("/{id}")
    @RequiresPermissions("permission-manage/delete")
    public ApiResult<Boolean> delete(@PathVariable Long id) {
        return ApiResult.success(systemPermissionService.removeById(id));
    }

    @GetMapping("/{id}")
    @RequiresPermissions("permission-manage/query")
    public ApiResult<SystemPermission> get(@PathVariable Long id){
        SystemPermission resource = systemPermissionService.getById(id);
        if (resource == null) {
            return ApiResult.noFound();
        }else {
            return ApiResult.success(resource);
        }
    }

    @RequiresPermissions("permission-manage/query")
    @GetMapping("/tree")
    public ApiResult<List<SystemPermissionDto>> tree(@RequestParam(required = false) Integer type) {
        return ApiResult.success(systemPermissionService.tree(null, true));
    }

    @RequiresPermissions("permission-manage/query")
    @GetMapping("/table-tree")
    public ApiResult<List<SystemPermissionDto>> tableTree(KeywordQueryParam param) {
        return ApiResult.success(systemPermissionService.tableTree(param));
    }

}
