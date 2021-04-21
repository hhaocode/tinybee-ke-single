package cn.tinybee.ke.admin.cms.controller;


import cn.tinybee.ke.biz.cms.entity.CmsClassify;
import cn.tinybee.ke.biz.cms.service.ICmsClassifyService;
import cn.tinybee.ke.common.web.ApiResult;
import cn.tinybee.ke.core.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 内容分类 前端控制器
 * </p>
 *
 * @author hao.huang
 * @since 2020-12-18
 */
@RestController
@RequestMapping("/api/cms-classify")
public class CmsClassifyController extends BaseController {

    @Autowired
    private ICmsClassifyService iCmsClassifyService;

    @GetMapping("/tree")
    private ApiResult tree () {
        return ApiResult.success(iCmsClassifyService.tree(null));
    }


    @GetMapping("/list/{type}")
    private ApiResult<List<CmsClassify>> listByType(@PathVariable Integer type) {
        return ApiResult.success(iCmsClassifyService.listByType(type, null));
    }


    @GetMapping("/directions")
    private ApiResult<List<CmsClassify>> directions() {
        return ApiResult.success(iCmsClassifyService.listByType(CmsClassify.TYPE_DIRECTION, null));
    }

    @GetMapping("/classifies")
    private ApiResult<List<CmsClassify>> classifies() {
        return ApiResult.success(iCmsClassifyService.listByType(CmsClassify.TYPE_CLASSIFY, null));
    }

    @GetMapping("/classifies/{pid}")
    private ApiResult<List<CmsClassify>> classifiesByDirectionPid(@PathVariable Long pid) {
        return ApiResult.success(iCmsClassifyService.listChildByPid(pid, true));
    }

    @PostMapping("/save")
    public ApiResult<Boolean> createOrModify (@RequestBody CmsClassify param) {
        return ApiResult.success(iCmsClassifyService.createOrModify(this.getOperator(), param));
    }

    @GetMapping("/children/{pid}")
    public ApiResult<List<CmsClassify>> listChildByPid (@PathVariable Long pid) {
        return ApiResult.success(iCmsClassifyService.listChildByPid(pid, null));
    }
}
