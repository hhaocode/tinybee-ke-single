package cn.tinybee.ke.admin.common.controller;

import cn.tinybee.ke.biz.base.entity.BaseDictItem;
import cn.tinybee.ke.biz.base.service.BaseDictService;
import cn.tinybee.ke.common.web.ApiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author huanghao
 * @version 1.0
 * @description
 * @date 2020/8/19 8:43
 */
@RestController
@RequestMapping("/api/common")
public class CommonController {

    @Autowired
    private BaseDictService baseDictService;


    @GetMapping("/dictItems/{dictCode}")
    public ApiResult<List<BaseDictItem>> dictItemsByDictCode(String dictCode) {
        return ApiResult.success(baseDictService.dictItems(dictCode));
    }

}
