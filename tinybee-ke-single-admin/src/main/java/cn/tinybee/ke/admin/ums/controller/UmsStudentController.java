package cn.tinybee.ke.admin.ums.controller;

import cn.tinybee.ke.biz.ums.entity.UmsStudent;
import cn.tinybee.ke.biz.ums.service.IUmsStudentService;
import cn.tinybee.ke.common.entity.PageParam;
import cn.tinybee.ke.common.web.ApiResult;
import cn.tinybee.ke.core.base.BaseController;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author huanghao
 * @version 1.0
 * @description
 * @date 2021/1/11 8:18
 */
@RestController
@RequestMapping("/api/ums/user")
public class UmsStudentController extends BaseController {

    @Autowired
    private IUmsStudentService iUmsStudentService;

    @GetMapping("/page")
    public ApiResult<Page<UmsStudent>> userPage (PageParam pageParam, @RequestParam(required = false) String mobile) {
        QueryWrapper<UmsStudent> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(mobile)) {
            queryWrapper.eq("mobile", mobile.trim());
        }
        return ApiResult.success(iUmsStudentService.page(this.pageConvert(pageParam), queryWrapper));
    }
}
