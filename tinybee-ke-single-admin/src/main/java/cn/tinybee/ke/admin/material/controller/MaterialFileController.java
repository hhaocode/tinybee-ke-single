package cn.tinybee.ke.admin.material.controller;


import cn.tinybee.ke.biz.material.entity.MaterialFile;
import cn.tinybee.ke.biz.material.service.IMaterialFileService;
import cn.tinybee.ke.common.entity.PageParam;
import cn.tinybee.ke.common.util.AssertUtils;
import cn.tinybee.ke.common.web.ApiResult;
import cn.tinybee.ke.core.base.BaseController;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author hao.huang
 * @since 2020-10-23
 */
@RestController
@RequestMapping("/api/material-file")
public class MaterialFileController extends BaseController {

    @Autowired
    private IMaterialFileService iMaterialFileService;

    /**
     * 上传的图片或者文件
     * @param files
     * @param type
     * @return
     */
    @PostMapping("/upload")
    public ApiResult<List<MaterialFile>> upload(@RequestParam(value = "files") MultipartFile[] files, @RequestParam("type") int type) {
        return ApiResult.success(iMaterialFileService.upload(files, type, this.getCurrentUserId()));
    }


    /**
     * @param param
     * @return
     */
    @PostMapping("/saveVod")
    public ApiResult<MaterialFile> saveVod(@RequestBody MaterialFile param) {
        return ApiResult.success(iMaterialFileService.saveOrModifyVod(param, this.getCurrentUserId()));
    }


    /**
     * 按分组查询
     * @param pageParam
     * @param groupId
     * @param name
     * @param type
     * @return
     */
    @GetMapping("/search")
    public ApiResult<Map> search (PageParam pageParam,
                                @RequestParam(required = false, value = "groupId") Long groupId,
                                @RequestParam(required = false, value = "name") String name,
                                @RequestParam(value = "type") int type
                                ) {
        return ApiResult.success(iMaterialFileService.search(this.pageConvert(pageParam),groupId, name, type, this.getCurrentUserId()));
    }


    @GetMapping("/pageSearch")
    public ApiResult<Page<MaterialFile>> pageSearch(
            PageParam pageParam,
            @RequestParam("type") Integer type,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "groupIds", required = false) List<Long> groupIds
    ) {
        return ApiResult.success(iMaterialFileService.pageQuery(this.pageConvert(pageParam), type, name, groupIds));
    }

    @GetMapping("/listByType")
    public ApiResult<List<MaterialFile>> list(@RequestParam("type") int type) {
        QueryWrapper<MaterialFile> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("type", type);
        queryWrapper.eq("delete", false);
        return ApiResult.success(iMaterialFileService.list(queryWrapper));
    }

    @DeleteMapping("/deleteBatch")
    public ApiResult<Boolean> deleteBatch (Long[] ids) {
        AssertUtils.notNull(ids, "请选择素材");
        return ApiResult.success(iMaterialFileService.deleteBatch(this.getOperator(), Arrays.asList(ids)));
    }

    @GetMapping("/getVodPlayAuth/{id}")
    public ApiResult getVodPlayAuth (@PathVariable  Long id) {
        return ApiResult.success(iMaterialFileService.getVodPlayAuth(id, true));
    }
}
