package cn.tinybee.ke.portal.open.controller;

import cn.tinybee.ke.biz.cms.dto.CmsCourseDto;
import cn.tinybee.ke.biz.cms.entity.CmsCourse;
import cn.tinybee.ke.biz.cms.service.ICmsCourseService;
import cn.tinybee.ke.common.entity.PageParam;
import cn.tinybee.ke.common.web.ApiResult;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static cn.tinybee.ke.common.util.PageUtils.pageConvert;

@Api(tags = "PublicApiController", description = "开放api")
@RestController
@RequestMapping("/api/public")
public class PublicApiController {

    @Autowired
    private ICmsCourseService ICmsCourseService;


    @ApiOperation("内容列表")
    @GetMapping("/content/list")
    public ApiResult<IPage<CmsCourse>> contentList(PageParam pageParam) {
        QueryWrapper<CmsCourse> queryWrapper = new QueryWrapper<>();
        IPage<CmsCourse> res = ICmsCourseService.page(pageConvert(pageParam), queryWrapper);
        return ApiResult.success(res);
    }

    @ApiOperation("根据ID获取内容")
    @GetMapping("/content/get/{contentId}")
    public ApiResult<CmsCourseDto> getContentById(@PathVariable Long contentId) {
        return ApiResult.success(ICmsCourseService.get(contentId));
    }

}
