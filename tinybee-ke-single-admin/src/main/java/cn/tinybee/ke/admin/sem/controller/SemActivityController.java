package cn.tinybee.ke.admin.sem.controller;


import cn.tinybee.ke.biz.sem.entity.SemActivity;
import cn.tinybee.ke.biz.sem.service.ISemActivityService;
import cn.tinybee.ke.common.entity.PageParam;
import cn.tinybee.ke.common.web.ApiResult;
import cn.tinybee.ke.core.base.BaseController;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author hao.huang
 * @since 2021-01-19
 */
@RestController
@RequestMapping("/api/sem/activity")
public class SemActivityController extends BaseController {

    @Autowired
    private ISemActivityService semActivityService;

    @PostMapping("/save")
    public ApiResult<Boolean> save (@RequestBody SemActivity param) {
        return ApiResult.success(semActivityService.saveOrModify(this.getOperator(), param));
    }

    @GetMapping("/page")
    public ApiResult<Page<SemActivity>> page (PageParam pageParam) {
        return ApiResult.success(semActivityService.pageRecords(this.getOperator(), this.pageConvert(pageParam)));
    }

    @GetMapping("/{id}")
    public ApiResult<SemActivity> get(@PathVariable Long id) {
        return ApiResult.success(semActivityService.get(id));
    }

    @DeleteMapping("/{id}")
    public ApiResult<Boolean> delete(@PathVariable Long id) {
        return ApiResult.success(semActivityService.delete(this.getOperator(), id));
    }

}
