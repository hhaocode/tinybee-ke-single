package cn.tinybee.ke.admin.base.controller;


import cn.tinybee.ke.biz.base.entity.BaseDictItem;
import cn.tinybee.ke.biz.base.service.BaseDictItemService;
import cn.tinybee.ke.core.base.BaseController;
import cn.tinybee.ke.common.web.ApiResult;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * <p>
 * 字典子项表 前端控制器
 * </p>
 *
 * @author hao.huang
 * @since 2020-08-17
 */
@RestController
@RequestMapping("/api/base/dict-item")
public class BaseDictItemController extends BaseController {


    private BaseDictItemService baseDictItemService;
    @Autowired
    public void setBaseDictItemService(BaseDictItemService baseDictItemService) {
        this.baseDictItemService = baseDictItemService;
    }

//    @RequiresPermissions("dictionary-manage/query")
    @GetMapping("/listByDictId/{dictId}")
    public ApiResult<List<BaseDictItem>> listByDictId(@PathVariable Long dictId) {
        QueryWrapper<BaseDictItem> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("dict_id", dictId);
        return ApiResult.success(baseDictItemService.list(queryWrapper));
    }

//    @RequiresPermissions("dictionary-manage/save")
    @PostMapping("/saveBatch/{dictId}")
    public ApiResult<Boolean> saveBatch(@RequestBody  List<BaseDictItem> list, @PathVariable Long dictId) {
        return ApiResult.success(baseDictItemService.saveBatch(list, dictId, this.getCurrentUserId()));
    }
}
