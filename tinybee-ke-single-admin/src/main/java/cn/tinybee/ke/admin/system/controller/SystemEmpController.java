package cn.tinybee.ke.admin.system.controller;


import cn.tinybee.ke.admin.system.dto.SystemLecturerDto;
import cn.tinybee.ke.admin.system.entity.SystemEmp;
import cn.tinybee.ke.admin.system.service.SystemEmpService;
import cn.tinybee.ke.admin.system.vo.SystemEmpQueryParam;
import cn.tinybee.ke.common.entity.KeywordQueryParam;
import cn.tinybee.ke.common.entity.PageParam;
import cn.tinybee.ke.common.web.ApiResult;
import cn.tinybee.ke.core.base.BaseController;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 员工表 前端控制器
 * </p>
 *
 * @author hao.huang
 * @since 2020-03-29
 */
@RestController
@RequestMapping("/api/system-emp")
public class SystemEmpController extends BaseController {

    @Autowired
    private SystemEmpService systemEmpService;

    @PostMapping("/save")
//    @RequiresPermissions("system-emp:save")
    public ApiResult<Boolean> save(@RequestBody SystemEmp emp) {
        if (systemEmpService.isExist(emp.getJobNo())) {
            ApiResult.failed(String.format("【%s】已经存在", emp.getJobNo()));
        }
        if (emp.getId() == null) {
            emp.setCreateTime(LocalDateTime.now());
            emp.setCreator(this.getCurrentUserId());
            emp.setDeleted(false);
        }
        return ApiResult.success(systemEmpService.saveOrUpdate(emp));
    }

    @GetMapping("/{id}")
//    @RequiresPermissions("system-emp:query")
    public ApiResult<SystemEmp> get(@PathVariable Long id) {
        SystemEmp role = systemEmpService.getById(id);
        if (role == null) {
            return ApiResult.noFound();
        }
        return ApiResult.success(role);
    }

    @DeleteMapping("/{id}")
//    @RequiresPermissions("system-emp:delete")
    public ApiResult<Boolean> delete(@PathVariable Long id) {
        return ApiResult.success(systemEmpService.removeById(id));
    }

    @GetMapping("/list")
//    @RequiresPermissions("system-emp:query")
    public ApiResult<List<SystemEmp>> list(KeywordQueryParam param){
        QueryWrapper<SystemEmp> queryWrapper = new QueryWrapper<>();
        List<SystemEmp> res = systemEmpService.list(queryWrapper);
        return ApiResult.success(res);
    }

    @GetMapping("/page")
//    @RequiresPermissions("system-emp:query")
    public ApiResult<IPage<SystemEmp>> page(PageParam pageParam, SystemEmpQueryParam param){
        QueryWrapper<SystemEmp> queryWrapper = new QueryWrapper<>();
        Long[] deptIds = param.getDeptIds();
        if(StringUtils.isNotBlank(param.getKw())) {
            queryWrapper.like("job_no",  "%" + param.getKw() + "%");
        }
        if (deptIds != null && deptIds.length > 0) {
            queryWrapper.in("dept_id", Arrays.asList(deptIds));
        }
        IPage<SystemEmp> res = systemEmpService.page(pageConvert(pageParam), queryWrapper);
        return ApiResult.success(res);
    }


//    @RequiresPermissions("system-emp:turn-lecturer")
    @PostMapping("/turn-lecturer/{empId}")
    public ApiResult<Boolean> turnLecturer(@PathVariable Long empId, @RequestBody SystemLecturerDto lecturerDto) {
        return ApiResult.success(systemEmpService.turnLecturer(empId, lecturerDto, this.getCurrentUserId()));
    }

}
