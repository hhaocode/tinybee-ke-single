package cn.tinybee.ke.admin.cms.controller;


import cn.tinybee.ke.biz.cms.dto.CmsCourseDto;
import cn.tinybee.ke.biz.cms.dto.CourseSearchParamDto;
import cn.tinybee.ke.biz.cms.entity.CmsCourse;
import cn.tinybee.ke.biz.cms.service.ICmsCourseService;
import cn.tinybee.ke.common.entity.GroupCount;
import cn.tinybee.ke.common.entity.PageParam;
import cn.tinybee.ke.common.web.ApiResult;
import cn.tinybee.ke.core.base.BaseController;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 内容主表 前端控制器
 * </p>
 *
 * @author hao.huang
 * @since 2020-04-10
 */
@RestController
@RequestMapping("/api/cms/course")
@Api(tags = "课程表")
public class CmsCourseController extends BaseController {

    @Autowired
    private ICmsCourseService iCmsCourseService;

    /**
     *
     * @param content
     * @return
     */
    @ApiOperation("保存课程")
    @PostMapping("/save")
    @RequiresPermissions("course-manage/save")
    public ApiResult<Boolean> save(@RequestBody @Validated CmsCourseDto content) {
        if (content.getId() == null) {
            content.setCreator(this.getCurrentUserId());
            content.setCreateTime(LocalDateTime.now());
        }
        if (content.getStatus() == null) {
            content.setStatus(CmsCourse.WAITING_ON_SHELF);
        }
        return ApiResult.success(iCmsCourseService.saveOrUpdateContent(this.getOperator(), content));
    }

    @RequiresPermissions("course-manage/query")
    @GetMapping("/page")
    public ApiResult<Page<CmsCourse>> page(PageParam pageParam, CourseSearchParamDto param){
        QueryWrapper<CmsCourse> queryWrapper = new QueryWrapper<>();
        if (param.getType() != null && param.getType() != 0) {
            queryWrapper.eq("type", param.getType());
        }
        Page<CmsCourse> res = iCmsCourseService.pageSearch(pageConvert(pageParam), param);
        return ApiResult.success(res);
    }

    @GetMapping("/get/{id}")
    @RequiresPermissions("course-manage/query")
    public  ApiResult<CmsCourseDto> get(@PathVariable Long id) {
        CmsCourseDto cmsContentDto = iCmsCourseService.get(id);
        if (cmsContentDto == null) {
            return ApiResult.noFound();
        }
        return ApiResult.success(cmsContentDto);
    }

    @RequiresPermissions("course-manage/delete")
    @DeleteMapping("/{id}")
    public ApiResult<Boolean> delete (@PathVariable Long id) {
        return ApiResult.success(iCmsCourseService.delete(this.getOperator(), id));
    }

    @RequiresPermissions("course-manage/putaway")
    @PutMapping("/putaway/{id}")
    public ApiResult<Boolean> putaway (@PathVariable Long id) {
        return ApiResult.success(iCmsCourseService.putaway(this.getOperator(), id));
    }

    @RequiresPermissions("course-manage/soldOut")
    @PutMapping("/soldOut/{id}")
    public ApiResult<Boolean> soldOut (@PathVariable Long id) {
        return ApiResult.success(iCmsCourseService.soldOut(this.getOperator(), id));
    }

    @GetMapping("/countByType")
    public ApiResult<Object> countByType () {
        List<GroupCount> data = iCmsCourseService.countByType(this.getOperator());
        Map<Long, Long> res = data.stream().collect(Collectors.toMap(GroupCount::getId, GroupCount::getCnt));
        return ApiResult.success(res);
    }

}
