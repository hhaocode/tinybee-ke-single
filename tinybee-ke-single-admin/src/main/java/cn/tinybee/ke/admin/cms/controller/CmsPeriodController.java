package cn.tinybee.ke.admin.cms.controller;


import cn.tinybee.ke.biz.cms.dto.CmsPeriodDto;
import cn.tinybee.ke.biz.cms.entity.CmsPeriod;
import cn.tinybee.ke.biz.cms.service.ICmsPeriodService;
import cn.tinybee.ke.common.web.ApiResult;
import cn.tinybee.ke.core.base.BaseController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 内容文章表 前端控制器
 * </p>
 *
 * @author hao.huang
 * @since 2020-04-10
 */
@RestController
@RequestMapping("/api/cms/period")
public class CmsPeriodController extends BaseController {

    @Autowired
    private ICmsPeriodService cmsPeriodService;

    @RequiresPermissions("cms-period/save")
    @PostMapping("/save")
    public ApiResult<CmsPeriod> save(@RequestBody CmsPeriod item) {
        return ApiResult.success(cmsPeriodService.saveOrModify(item, this.getCurrentUserId()));
    }

    @RequiresPermissions("cms-period/update")
    @PutMapping("/update/{id}")
    public ApiResult<Boolean> update(@RequestBody CmsPeriod item, @PathVariable Long id) {
        item.setId(id);
        return ApiResult.success(cmsPeriodService.updateById(item));
    }

    @RequiresPermissions("cms-period/delete")
    @DeleteMapping("/delete/{id}")
    public ApiResult<Boolean> delete(@PathVariable Long id) {
        return ApiResult.success(cmsPeriodService.removeById(id));
    }

    @PutMapping("/doUpdate/{id}")
    public ApiResult<Boolean> doUpdate(@PathVariable Long id) {
        return ApiResult.success(cmsPeriodService.doUpdate(this.getOperator() ,id));
    }

    @GetMapping("/map-item/{contentId}")
    public ApiResult<Map<Long, List<CmsPeriodDto>>> mapItemByContentId(@PathVariable Long contentId) {
        return ApiResult.success(cmsPeriodService.mapItemByContentId(contentId));
    }

//    @RequiresPermissions("cms-period/query")
    @GetMapping("/getPeriodById/{id}")
    public ApiResult<CmsPeriod> getPeriodById (@PathVariable Long id) {
        return ApiResult.success(cmsPeriodService.getPeriodById(id));
    }

//    @RequiresPermissions("cms-period/query")
    @GetMapping("/periods/{courseId}")
    public ApiResult<List<CmsPeriod>> periods (@PathVariable Long courseId) {
        return ApiResult.success(cmsPeriodService.periodsByCourseId(courseId));
    }
}
