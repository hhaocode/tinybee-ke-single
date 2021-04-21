package cn.tinybee.ke.admin.ums.controller;


import cn.tinybee.ke.core.base.BaseController;
import cn.tinybee.ke.biz.ums.entity.UmsLecturer;
import cn.tinybee.ke.biz.ums.service.IUmsLecturerService;
import cn.tinybee.ke.common.entity.PageParam;
import cn.tinybee.ke.common.web.ApiResult;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 讲师信息表 前端控制器
 *
 *
 * </p>
 *
 * @author hao.huang
 * @since 2020-07-13
 */
@RestController
@RequestMapping("/api/ums/lecturer")
public class UmsLecturerController extends BaseController {


    @Autowired
    private IUmsLecturerService iUmsLecturerService;

    @GetMapping("/page")
    public ApiResult<Page<UmsLecturer>> page(PageParam pageParam) {
        Page page = iUmsLecturerService.page(pageConvert(pageParam));
        return ApiResult.success(page);
    }
}
