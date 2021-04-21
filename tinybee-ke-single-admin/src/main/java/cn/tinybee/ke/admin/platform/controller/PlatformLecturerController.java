package cn.tinybee.ke.admin.platform.controller;


import cn.tinybee.ke.biz.platform.entity.PlatformLecturer;
import cn.tinybee.ke.biz.platform.service.IPlatformLecturerService;
import cn.tinybee.ke.common.entity.PageParam;
import cn.tinybee.ke.common.web.ApiResult;
import cn.tinybee.ke.core.base.BaseController;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师信息表 前端控制器
 * </p>
 *
 * @author hao.huang
 * @since 2021-01-28
 */
@RestController
@RequestMapping("/api/platform/lecturer")
public class PlatformLecturerController extends BaseController {

    @Autowired
    private IPlatformLecturerService lecturerService;

    @GetMapping("/page")
    public ApiResult<Page<PlatformLecturer>> page (PageParam pageParam, @RequestParam(required = false) String name) {
        QueryWrapper<PlatformLecturer> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(name)) {
            queryWrapper.like("name", name);
        }
        Page<PlatformLecturer> page = lecturerService.page(this.pageConvert(pageParam), queryWrapper);
        return ApiResult.success(page);
    }

    @RequiresPermissions("lecturer/save")
    @PostMapping("/save")
    public ApiResult<Boolean> save (@RequestBody @Validated PlatformLecturer param) {
        return ApiResult.success(lecturerService.saveOrModify(this.getOperator(), param));
    }


    @GetMapping("/{id}")
    public ApiResult<PlatformLecturer> getById (@PathVariable Long id) {
        return ApiResult.success(lecturerService.getDetailById(id));
    }

    @DeleteMapping("{id}")
    public ApiResult<Boolean> deleteById (@PathVariable Long id) {
        return ApiResult.success(lecturerService.deleteById(this.getOperator(), id));
    }

    @GetMapping("/list")
    public ApiResult<List<PlatformLecturer>> list () {
        QueryWrapper<PlatformLecturer> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("deleted", false);
        queryWrapper.eq("available", true);
        return ApiResult.success(lecturerService.list(queryWrapper));
    }
}
