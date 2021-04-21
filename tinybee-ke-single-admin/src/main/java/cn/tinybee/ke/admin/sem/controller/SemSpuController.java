package cn.tinybee.ke.admin.sem.controller;


import cn.tinybee.ke.biz.sem.service.ISemSpuService;
import cn.tinybee.ke.core.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 商品表 前端控制器
 * </p>
 *
 * @author hao.huang
 * @since 2021-03-15
 */
@RestController
@RequestMapping("/api/sem/spu")
public class SemSpuController extends BaseController {

    private ISemSpuService semSpuService;

    @Autowired
    public void setSemSpuService(ISemSpuService semSpuService) {
        this.semSpuService = semSpuService;
    }
}
