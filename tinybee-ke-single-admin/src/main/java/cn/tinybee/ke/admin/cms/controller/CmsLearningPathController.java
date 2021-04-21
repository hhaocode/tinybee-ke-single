package cn.tinybee.ke.admin.cms.controller;


import cn.tinybee.ke.biz.cms.entity.CmsLearningPath;
import cn.tinybee.ke.biz.cms.service.ICmsLearningPathService;
import cn.tinybee.ke.common.web.ApiResult;
import cn.tinybee.ke.core.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 学习路径 前端控制器
 * </p>
 *
 * @author hao.huang
 * @since 2021-03-14
 */
@RestController
@RequestMapping("/api/cms/learning-path")
public class CmsLearningPathController extends BaseController {

    private ICmsLearningPathService cmsLearningPathService;

    @Autowired
    public void setCmsLearningPathService(ICmsLearningPathService cmsLearningPathService) {
        this.cmsLearningPathService = cmsLearningPathService;
    }

    @GetMapping("/listPath")
    public ApiResult<List<CmsLearningPath>> listPath (@RequestParam(required = false) String kw) {
        return ApiResult.success(cmsLearningPathService.listPath(kw));
    }

    @PostMapping("/save")
    public ApiResult<CmsLearningPath> save (@RequestBody @Validated CmsLearningPath param) {
        return ApiResult.success(cmsLearningPathService.saveOrModify(this.getOperator(), param));
    }

    @GetMapping("/getDetailById/{id}")
    public ApiResult<CmsLearningPath> getDetailById (@PathVariable Long id) {
        return ApiResult.success(cmsLearningPathService.getDetailById(id));
    }

    @DeleteMapping
    public ApiResult<Boolean> deleteById (@PathVariable Long id) {
        return ApiResult.success(cmsLearningPathService.deleteById(id));
    }
}
