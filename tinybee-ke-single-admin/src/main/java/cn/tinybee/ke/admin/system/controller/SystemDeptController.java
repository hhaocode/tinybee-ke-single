package cn.tinybee.ke.admin.system.controller;


import cn.tinybee.ke.admin.system.dto.SystemDeptDto;
import cn.tinybee.ke.admin.system.entity.SystemDept;
import cn.tinybee.ke.admin.system.service.SystemDeptService;
import cn.tinybee.ke.common.entity.KeywordQueryParam;
import cn.tinybee.ke.common.web.ApiResult;
import cn.tinybee.ke.core.base.BaseController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 组织表 前端控制器
 * </p>
 *
 * @author hao.huang
 * @since 2020-03-29
 */
@RestController
@RequestMapping(SystemDeptController.API_PREFIX)
public class SystemDeptController extends BaseController {

    protected static final String API_PREFIX = "/api/system-dept";

    @Autowired
    private SystemDeptService systemDeptService;

    @RequiresPermissions("system-dept:save")
    @PostMapping("/save")
    public ApiResult<Boolean> save(@RequestBody @Validated SystemDept resource) {
        if (resource.getDeleted() == null) {
            resource.setDeleted(false);
        }
        if(resource.getPid() == null) {
            resource.setPid(0L);
        }
        return ApiResult.success(systemDeptService.saveOrUpdate(resource));
    }

    @RequiresPermissions("system-dept:delete")
    @DeleteMapping("/{id}")
    public ApiResult<Boolean> delete(@PathVariable Long id) {
        return ApiResult.success(systemDeptService.removeById(id));
    }

    @RequiresPermissions("system-dept:query")
    @GetMapping("/{id}")
    public ApiResult<SystemDept> get(@PathVariable Long id){
        SystemDept resource = systemDeptService.getById(id);
        if (resource == null) {
            return ApiResult.noFound();
        }else {
            return ApiResult.success(resource);
        }
    }

    @RequiresPermissions("system-dept:query")
    @GetMapping("/tree")
    public ApiResult<List<SystemDeptDto>> tree(@RequestParam(required = false) Integer type) {
        return ApiResult.success(systemDeptService.tree());
    }

    @RequiresPermissions("system-dept:query")
    @GetMapping("/table-tree")
    public ApiResult<List<SystemDeptDto>> tableTree(KeywordQueryParam param) {
        return ApiResult.success(systemDeptService.tableTree(param));
    }

}
