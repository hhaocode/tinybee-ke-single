package cn.tinybee.ke.admin.system.controller;


import cn.tinybee.ke.admin.system.entity.SystemPost;
import cn.tinybee.ke.admin.system.service.SystemPostService;
import cn.tinybee.ke.common.entity.KeywordQueryParam;
import cn.tinybee.ke.common.entity.PageParam;
import cn.tinybee.ke.common.web.ApiResult;
import cn.tinybee.ke.core.base.BaseController;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 岗位表 前端控制器
 * </p>
 *
 * @author hao.huang
 * @since 2020-03-30
 */
@RestController
@RequestMapping("/api/system-post")
public class SystemPostController extends BaseController {

    @Autowired
    private SystemPostService systemPostService;

    @PostMapping("/save")
    public ApiResult<Boolean> save(@RequestBody SystemPost post) {
        if (post.getId() == null) {
            post.setAvailable(true);
            post.setCreator(this.getCurrentUserId());
            post.setCreateTime(LocalDateTime.now());
        }
       return ApiResult.success( systemPostService.save(post));
    }

    @GetMapping("/{id}")
    public ApiResult<SystemPost> get(@PathVariable Long id){
        SystemPost post = systemPostService.getById(id);
        if (post == null) {
            return ApiResult.noFound();
        }else {
            return ApiResult.success(post);
        }
    }

    @DeleteMapping("/{id}")
    public ApiResult<Boolean> delete(@PathVariable Long id){
        return ApiResult.success(systemPostService.removeById(id));
    }

    @GetMapping("/list")
    public ApiResult<List<SystemPost>> list(){
        return ApiResult.success(systemPostService.list());
    }

    @GetMapping("/page")
    public ApiResult<IPage<SystemPost>> page(PageParam pageParam, KeywordQueryParam param){
        QueryWrapper<SystemPost> queryWrapper = new QueryWrapper<>();
        IPage<SystemPost> res = systemPostService.page(pageConvert(pageParam), queryWrapper);
        return ApiResult.success(res);
    }
}
