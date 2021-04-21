package cn.tinybee.ke.admin.cms.controller;


import cn.tinybee.ke.biz.cms.entity.CmsLearningPathStage;
import cn.tinybee.ke.biz.cms.service.ICmsLearningPathStageService;
import cn.tinybee.ke.common.web.ApiResult;
import cn.tinybee.ke.core.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 学习路径阶段 前端控制器
 * </p>
 *
 * @author hao.huang
 * @since 2021-03-14
 */
@RestController
@RequestMapping("/api/cms/learning-path-stage")
public class CmsLearningPathStageController extends BaseController {


    private ICmsLearningPathStageService cmsLearningPathStageService;

    @Autowired
    public void setCmsLearningPathStageService(ICmsLearningPathStageService cmsLearningPathStageService) {
        this.cmsLearningPathStageService = cmsLearningPathStageService;
    }

    @PostMapping("/save")
    public ApiResult<Boolean> save (@RequestBody @Validated CmsLearningPathStage param) {
        return ApiResult.success(cmsLearningPathStageService.saveOrModify(this.getOperator(), param));
    }

    @DeleteMapping("/delete/{id}")
    public ApiResult<Boolean> delete (@PathVariable Long id) {
        return ApiResult.success(cmsLearningPathStageService.deleteById(this.getOperator(), id));
    }
}
