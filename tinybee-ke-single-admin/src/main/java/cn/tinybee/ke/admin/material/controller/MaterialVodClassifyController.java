package cn.tinybee.ke.admin.material.controller;


import cn.tinybee.ke.biz.material.entity.MaterialVodClassify;
import cn.tinybee.ke.biz.material.service.IMaterialVodClassifyService;
import cn.tinybee.ke.common.enums.VodType;
import cn.tinybee.ke.common.handle.tree.TreeNode;
import cn.tinybee.ke.common.web.ApiResult;
import cn.tinybee.ke.core.base.BaseController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author hao.huang
 * @since 2020-10-23
 */
@RestController
@RequestMapping("/api/material-vod-classify")
public class MaterialVodClassifyController extends BaseController {

    @Autowired
    private IMaterialVodClassifyService iMaterialVodClassifyService;

    @GetMapping("/tree")
    public ApiResult<List<TreeNode<MaterialVodClassify>>> tree (@RequestParam("type") VodType type) {
        return ApiResult.success(iMaterialVodClassifyService.tree(type));
    }

    @PostMapping("/saveOrUpdate")
    public ApiResult<MaterialVodClassify> saveOrUpdate (@RequestBody MaterialVodClassify materialVodClassify) {
        return ApiResult.success(iMaterialVodClassifyService.saveOrUpdate(materialVodClassify, this.getCurrentUserId()));
    }

}
