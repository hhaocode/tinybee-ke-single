package cn.tinybee.ke.admin.cms.controller;


import cn.tinybee.ke.biz.cms.dto.CmsChapterPeriodsDto;
import cn.tinybee.ke.biz.cms.entity.CmsChapter;
import cn.tinybee.ke.biz.cms.service.ICmsChapterService;
import cn.tinybee.ke.common.web.ApiResult;
import cn.tinybee.ke.core.base.BaseController;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 章节表 前端控制器
 * </p>
 *
 * @author hao.huang
 * @since 2020-04-10
 */
@RestController
@RequestMapping("/api/cms/chapter")
public class CmsChapterController extends BaseController {

    @Autowired
    private ICmsChapterService iCmsChapterService;

//    @RequiresPermissions("cms-period/query")
    @GetMapping("/list/{contentId}")
    public ApiResult<List<CmsChapter>> listChapterByContentId(@PathVariable Long contentId) {
        QueryWrapper<CmsChapter> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_id", contentId);
        return ApiResult.success(iCmsChapterService.list(queryWrapper));
    }

    @RequiresPermissions("cms-period/save")
    @PostMapping("/save")
    public ApiResult<Boolean> save(@RequestBody CmsChapter chapter) {
        if (chapter.getId() == null) {
            chapter.setCreator(this.getCurrentUserId());
            chapter.setCreateTime(LocalDateTime.now());
        }
        return ApiResult.success(iCmsChapterService.saveOrUpdate(chapter));
    }

    @RequiresPermissions("cms-period/delete")
    @DeleteMapping("/delete/{id}")
    public ApiResult<Boolean> delete(@PathVariable Long id) {
        //删除了
        return ApiResult.success(iCmsChapterService.delete(id));
    }

//    @RequiresPermissions("cms-period/query")
    @GetMapping("/periods/{contentId}")
    public ApiResult<List<CmsChapterPeriodsDto>> listChapterContentItemByContentId(@PathVariable Long contentId) {
        return ApiResult.success(iCmsChapterService.listChapterContentItemByContentId(contentId, false));
    }


//    @RequiresPermissions("cms-period/query")
    @GetMapping("/get/{id}")
    public ApiResult<CmsChapter> getById (@PathVariable Long id) {
        return ApiResult.success(iCmsChapterService.getById(id));
    }

}
