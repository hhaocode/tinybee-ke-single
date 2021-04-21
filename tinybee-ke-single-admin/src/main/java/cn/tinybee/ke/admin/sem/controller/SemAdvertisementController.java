package cn.tinybee.ke.admin.sem.controller;


import cn.tinybee.ke.biz.material.entity.MaterialFile;
import cn.tinybee.ke.biz.material.service.IMaterialFileService;
import cn.tinybee.ke.biz.sem.entity.SemAdvertisement;
import cn.tinybee.ke.biz.sem.service.ISemAdvertisementService;
import cn.tinybee.ke.common.entity.PageParam;
import cn.tinybee.ke.common.web.ApiResult;
import cn.tinybee.ke.core.base.BaseController;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * 广告信息 前端控制器
 * </p>
 *
 * @author hao.huang
 * @since 2020-11-04
 */
@RestController
@RequestMapping("/api/sem-advertisement")
public class SemAdvertisementController extends BaseController {

    @Autowired
    private ISemAdvertisementService iSemAdvertisementService;

    @Autowired
    private IMaterialFileService iMaterialFileService;


    @GetMapping("/page")
    public ApiResult<Page<SemAdvertisement>> page (PageParam pageParam) {
        Page<SemAdvertisement> page = iSemAdvertisementService.page(this.pageConvert(pageParam));
        return ApiResult.success(page);
    }

    @PostMapping("/save")
    public ApiResult<SemAdvertisement> save (@RequestBody SemAdvertisement semAdvertisement) {
        return ApiResult.success(iSemAdvertisementService.save(semAdvertisement, this.getCurrentUserId()));
    }

    @DeleteMapping("/delete/{id}")
    public ApiResult<Boolean> deleteById (@PathVariable Long id) {
        return ApiResult.success(iSemAdvertisementService.removeById(id));
    }


    @PutMapping("/available/{id}")
    public ApiResult<Boolean> available (@PathVariable Long id, @RequestParam Boolean available) {
        return ApiResult.success(iSemAdvertisementService.available(id, available, this.getCurrentUserId()));
    }

}
