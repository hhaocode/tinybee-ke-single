package cn.tinybee.ke.admin.material.controller;


import cn.tinybee.ke.biz.material.entity.MaterialArticle;
import cn.tinybee.ke.biz.material.service.IMaterialArticleService;
import cn.tinybee.ke.common.entity.PageParam;
import cn.tinybee.ke.common.web.ApiResult;
import cn.tinybee.ke.core.base.BaseController;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author hao.huang
 * @since 2020-12-23
 */
@Controller
@ResponseBody
@RequestMapping("/api/material/article")
public class MaterialArticleController extends BaseController {

    @Autowired
    private IMaterialArticleService iMaterialArticleService;

    @RequiresPermissions("article-manage/query")
    @GetMapping("/page")
    public ApiResult<Page<MaterialArticle>> page (PageParam pageParam) {
        return ApiResult.success(iMaterialArticleService.page(this.getOperator(), this.pageConvert(pageParam)));
    }

    @RequiresPermissions("article-manage/save")
    @PostMapping("/save")
    public ApiResult<MaterialArticle> save (@RequestBody @Validated MaterialArticle param) {
        return ApiResult.success(iMaterialArticleService.saveOrModify(this.getOperator(), param));
    }


    @RequiresPermissions("article-manage/query")
    @GetMapping("/{id}")
    public ApiResult<MaterialArticle> get (@PathVariable Long id) {
        return ApiResult.success(iMaterialArticleService.getById(id));
    }

    @RequiresPermissions("article-manage/delete")
    @DeleteMapping("/{id}")
    public ApiResult<Boolean> delete (@PathVariable Long id) {
        return ApiResult.success(iMaterialArticleService.delete(this.getOperator(), id));
    }

}
