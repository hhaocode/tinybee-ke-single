package cn.tinybee.ke.admin.cms.controller;


import cn.tinybee.ke.biz.cms.service.ICmsMediaService;
import cn.tinybee.ke.common.config.properties.TinybeeVideoProperties;
import cn.tinybee.ke.common.web.ApiResult;
import cn.tinybee.ke.core.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 视频信息 前端控制器
 * </p>
 *
 * @author hao.huang
 * @since 2020-06-07
 */
@RestController
@RequestMapping("/api/cms/media")
public class CmsMediaController extends BaseController {


    @Autowired
    private ICmsMediaService ICmsMediaService;

    @Autowired
    private TinybeeVideoProperties tinybeeVideoProperties;


    /**
     * 上传
     * @param file
     * @param module
     * @return
     */
    @PostMapping("/upload")
    public ApiResult<Long> upload(MultipartFile file, @RequestParam Integer module) {
        return ApiResult.success(ICmsMediaService.upload(file, module));
    }
    

}
