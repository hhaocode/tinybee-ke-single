package cn.tinybee.ke.admin.cms.controller;


import cn.tinybee.ke.biz.cms.entity.CmsVod;
import cn.tinybee.ke.biz.cms.service.ICmsVodService;
import cn.tinybee.ke.common.entity.PageParam;
import cn.tinybee.ke.common.util.PageUtils;
import cn.tinybee.ke.common.web.ApiResult;
import cn.tinybee.ke.core.base.BaseController;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 视频点播 前端控制器
 * </p>
 *
 * @author hao.huang
 * @since 2020-09-29
 */
@RestController
@RequestMapping("/api/cms-vod")
public class CmsVodController extends BaseController {
    // 音视频

    @Autowired
    private ICmsVodService iCmsVodService;

    /**
     *
     * @param file
     * @return
     */
    @PostMapping("/uploadVideo")
    public ApiResult<CmsVod> uploadVideo (@RequestParam("file") MultipartFile file) {
        //
        return ApiResult.success(iCmsVodService.uploadVideo(file, this.getCurrentUserId()));
    }


    @PostMapping("/uploadAudio")
    public ApiResult<CmsVod> uploadAudio (@RequestParam("file") MultipartFile file) {
        return ApiResult.success();
    }

    @GetMapping("/page")
    public ApiResult<Page<CmsVod>> page(PageParam pageParam) {
        Page page = PageUtils.pageConvert(pageParam);
        return ApiResult.success(iCmsVodService.page(page));
    }

}
