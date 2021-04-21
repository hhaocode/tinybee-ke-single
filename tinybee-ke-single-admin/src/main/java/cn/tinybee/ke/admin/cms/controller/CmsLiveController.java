package cn.tinybee.ke.admin.cms.controller;


import cn.tinybee.ke.biz.cms.entity.CmsLive;
import cn.tinybee.ke.biz.cms.service.ICmsLiveService;
import cn.tinybee.ke.common.entity.PageParam;
import cn.tinybee.ke.common.web.ApiResult;
import cn.tinybee.ke.core.base.BaseController;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author hao.huang
 * @since 2021-01-25
 */
@RestController
@RequestMapping("/api/cms/live")
public class CmsLiveController extends BaseController {

    private ICmsLiveService cmsLiveService;

    @Autowired
    public void setCmsLiveService(ICmsLiveService cmsLiveService) {
        this.cmsLiveService = cmsLiveService;
    }

    @GetMapping("/page")
    public ApiResult<Page<CmsLive>> page (PageParam page, CmsLive param) {
        return ApiResult.success(cmsLiveService.pageQuery(this.pageConvert(page), param));
    }
}
