package cn.tinybee.ke.admin.base.controller;


import cn.tinybee.ke.biz.base.entity.BaseDict;
import cn.tinybee.ke.biz.base.service.BaseDictService;
import cn.tinybee.ke.common.entity.PageParam;
import cn.tinybee.ke.common.web.ApiResult;
import cn.tinybee.ke.core.base.BaseController;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 字典表 前端控制器
 * </p>
 *
 * @author hao.huang
 * @since 2020-08-17
 */
@RestController
@RequestMapping("/api/base/dict")
public class BaseDictController extends BaseController {

    @Autowired
    private BaseDictService baseDictService;

    @GetMapping("/page")
//    @RequiresPermissions("dictionary-manage/query")
    public ApiResult<Page> page(PageParam pageParam) {
        return ApiResult.success(baseDictService.page(pageConvert(pageParam)));
    }

    @PostMapping("/save")
//    @RequiresPermissions("dictionary-manage/save")
    public ApiResult<Boolean> save (@RequestBody BaseDict dict) {
        return ApiResult.success(baseDictService.saveOrUpdate(dict, this.getCurrentUserId()));
    }

    @DeleteMapping("/{id}")
//    @RequiresPermissions("dictionary-manage/delete")
    public ApiResult<Boolean> deleteById (@PathVariable Long id) {
        return ApiResult.success(baseDictService.deleteById(id));
    }

}
