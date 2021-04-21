package cn.tinybee.ke.admin.cms.controller;


import cn.tinybee.ke.biz.cms.entity.CmsFile;
import cn.tinybee.ke.biz.cms.entity.CmsImage;
import cn.tinybee.ke.biz.cms.service.ICmsFileService;
import cn.tinybee.ke.common.entity.PageParam;
import cn.tinybee.ke.common.enums.FileTypeEnum;
import cn.tinybee.ke.common.web.ApiResult;
import cn.tinybee.ke.core.base.BaseController;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * <p>
 * oss上文件 前端控制器
 * </p>
 *
 * @author hao.huang
 * @since 2020-09-29
 */
@RestController
@RequestMapping("/api/cms-file")
public class CmsFileController extends BaseController {

    // 文件管理

    @Autowired
    private ICmsFileService cmsFileService;


    @PostMapping("/upload")
    public ApiResult<CmsFile> upload(@RequestParam(value = "files") MultipartFile[] files, @RequestParam("type") FileTypeEnum type) {
        return ApiResult.success(cmsFileService.upload(files, type));
    }

    @PostMapping("/uploadFile")
    public ApiResult<List<CmsImage>> uploadFile (@RequestParam(value = "files") MultipartFile[] files) throws IOException {
        return ApiResult.success(cmsFileService.uploadImage(files));
    }

    @GetMapping("/imagePage")
    public ApiResult<Page<CmsFile>> imagePage (PageParam pageParam) {
        return ApiResult.success(cmsFileService.imagePage(pageParam));
    }

}
