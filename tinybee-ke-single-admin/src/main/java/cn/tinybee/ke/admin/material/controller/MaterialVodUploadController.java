package cn.tinybee.ke.admin.material.controller;

import cn.tinybee.ke.biz.material.entity.MaterialVodUpload;
import cn.tinybee.ke.biz.material.service.IMaterialVodUploadService;
import cn.tinybee.ke.common.enums.VodType;
import cn.tinybee.ke.common.service.cloud.domain.VodPlayAuthResult;
import cn.tinybee.ke.common.web.ApiResult;
import cn.tinybee.ke.core.base.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * 音视频点播上传
 * @author huanghao
 * @version 1.0
 * @description
 * @date 2020/12/22 15:40
 */
@RestController
@RequestMapping("/api/material/vod")
@Api("音视频点播上传")
public class MaterialVodUploadController extends BaseController {

    @Autowired
    private IMaterialVodUploadService iMaterialVodUploadService;

    @ApiOperation("上传")
    @RequiresPermissions("vod-manage/upload")
    @PostMapping("/upload")
    public ApiResult<MaterialVodUpload> upload(@RequestParam("file") MultipartFile file, @RequestParam ("type") int type) {
        return ApiResult.success(iMaterialVodUploadService.upload(this.getOperator(), file, type));
    }


    @ApiOperation("获取原信息")
    @GetMapping("/info/{id}")
    public ApiResult<VodPlayAuthResult.MetaInfo> info (@PathVariable Long id) {
        return ApiResult.success(iMaterialVodUploadService.info(id));
    }
}
