package cn.tinybee.ke.admin.material.controller;

import cn.tinybee.ke.biz.material.dto.MaterialDto;
import cn.tinybee.ke.biz.material.service.IMaterialService;
import cn.tinybee.ke.common.entity.PageParam;
import cn.tinybee.ke.common.web.ApiResult;
import cn.tinybee.ke.core.base.BaseController;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author huanghao
 * @version 1.0
 * @description
 * @date 2020/12/22 12:50
 */
@RestController
@RequestMapping("/api/material")
public class MaterialController extends BaseController {

    @Autowired
    private IMaterialService iMaterialService;

    @GetMapping("/groupPage")
    public ApiResult groupPage(PageParam pageParam,
                               @RequestParam int type,
                               @RequestParam(required = false) Long groupId,
                               @RequestParam(required = false) String name
                               ) {
        Page<MaterialDto> page = iMaterialService.page(this.getOperator(), this.pageConvert(pageParam), type, groupId, name);
        return ApiResult.success(page);
    }
}
