package cn.tinybee.ke.admin.sem.controller;


import cn.tinybee.ke.biz.sem.service.ISemPriceService;
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
 * @since 2021-03-15
 */
@RestController
@RequestMapping("/api/sem/price")
public class SemPriceController extends BaseController {

    @Autowired
    private ISemPriceService semPriceService;

}
