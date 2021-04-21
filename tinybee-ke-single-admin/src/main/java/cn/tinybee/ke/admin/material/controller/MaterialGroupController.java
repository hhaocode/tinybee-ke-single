package cn.tinybee.ke.admin.material.controller;


import cn.tinybee.ke.biz.material.dto.MaterialDto;
import cn.tinybee.ke.biz.material.entity.MaterialGroup;
import cn.tinybee.ke.biz.material.service.IMaterialGroupService;
import cn.tinybee.ke.common.entity.PageParam;
import cn.tinybee.ke.common.web.ApiResult;
import cn.tinybee.ke.core.base.BaseController;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author hao.huang
 * @since 2020-12-23
 */
@Api("素材分组")
@RestController
@RequestMapping("/api/material/group")
public class MaterialGroupController extends BaseController {

    @Autowired
    private IMaterialGroupService materialGroupService;

    @ApiOperation("根据类型查询素材分组")
    @GetMapping("/list/{type}")
    public ApiResult<List<MaterialGroup>> listByType (@PathVariable Integer type) {
        return ApiResult.success(materialGroupService.listByType(type, true));
    }

    @ApiOperation("根据类型查询素材分组无全部")
    @GetMapping("/list/noAll/{type}")
    public ApiResult<List<MaterialGroup>> noAll (@PathVariable Integer type) {
        return ApiResult.success(materialGroupService.listByType(type, false));
    }

    @GetMapping("/page")
    public ApiResult<Page<MaterialGroup>> page (PageParam pageParam, @RequestParam(required = false) Integer type) {
        QueryWrapper<MaterialGroup> queryWrapper = new QueryWrapper<>();
        if (type != null) {
            queryWrapper.eq("type", type);
        }
        queryWrapper.orderByAsc("sort");
        return ApiResult.success(materialGroupService.page(this.pageConvert(pageParam), queryWrapper));
    }

    @ApiOperation("根据类型查询map")
    @GetMapping("/map/{type}")
    public ApiResult<List<MaterialGroup>> map (@PathVariable Integer type) {
        return ApiResult.success(materialGroupService.listByType(type, true));
    }

    /**
     * 保存
     * @param param
     * @return
     */
    @ApiOperation("保存素材分组")
    @PostMapping("/save")
    public ApiResult<Boolean> save (@RequestBody MaterialGroup param) {
        return ApiResult.success(materialGroupService.saveOrModify(this.getOperator(), param));
    }

    @ApiOperation("删除素材分组")
    @DeleteMapping("/{id}")
    public ApiResult<Boolean> delete (@PathVariable Long id) {
        return ApiResult.success(materialGroupService.deleteById(this.getOperator(), id));
    }

}
