package cn.tinybee.ke.admin.material.controller;


import cn.tinybee.ke.biz.material.service.IMaterialFileGroupService;
import cn.tinybee.ke.core.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author hao.huang
 * @since 2020-10-23
 */
@RestController
@RequestMapping("/api/material-file-group")
public class MaterialFileGroupController extends BaseController {

    @Autowired
    private IMaterialFileGroupService iMaterialFileGroupService;

}
