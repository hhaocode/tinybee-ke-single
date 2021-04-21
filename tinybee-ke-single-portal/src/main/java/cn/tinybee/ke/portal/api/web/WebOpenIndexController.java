package cn.tinybee.ke.portal.api.web;

import cn.hutool.core.util.RandomUtil;
import cn.tinybee.ke.biz.cms.entity.CmsClassify;
import cn.tinybee.ke.biz.cms.entity.CmsCourse;
import cn.tinybee.ke.biz.cms.service.ICmsClassifyService;
import cn.tinybee.ke.biz.cms.service.ICmsCourseService;
import cn.tinybee.ke.common.web.ApiResult;
import cn.tinybee.ke.portal.api.dto.SubjectCourse;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author huanghao
 * @version 1.0
 * @description
 * @date 2021/1/15 12:28
 *
 *
 * 1 路线
 * 2 秒杀
 * 3 拼团
 * 4 上新
 * 5 兴趣推荐
 *
 *
 */
@RestController
@RequestMapping("/api/open/web/index")
public class WebOpenIndexController {

    @Autowired
    private ICmsCourseService cmsCourseService;

    private ExecutorService executorService = Executors.newFixedThreadPool(5);

    @Autowired
    private ICmsClassifyService cmsClassifyService;

    @GetMapping("/recommend/entry")
    public ApiResult<List<SubjectCourse>> recommendEntry () {
        //
        // 多线程
        List<SubjectCourse> res = new ArrayList<>();
        Future<Page<CmsCourse>> submit = executorService.submit(() -> {
            QueryWrapper<CmsCourse> queryWrapper = new QueryWrapper<>();
            queryWrapper.orderByDesc("on_shelf_time");
            Page<CmsCourse> page = cmsCourseService.page(new Page<>(1, 8), queryWrapper);
            return page;
        });
        List<CmsClassify> cmsClassifies = cmsClassifyService.listByType(1, true);
        int i = RandomUtil.randomInt(0, cmsClassifies.size());
        Future<Page<CmsCourse>> direction = executorService.submit(() -> {
            QueryWrapper<CmsCourse> queryWrapper = new QueryWrapper<>();
//            queryWrapper.eq("")
//            queryWrapper.orderByDesc("on_shelf_time");
            Page<CmsCourse> page = cmsCourseService.page(new Page<>(1, 8), queryWrapper);
            return page;
        });
        Future<Page<CmsCourse>> free = executorService.submit(() -> {
            QueryWrapper<CmsCourse> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("free", true);
//            queryWrapper.orderByDesc("on_shelf_time");
            Page<CmsCourse> page = cmsCourseService.page(new Page<>(1, 8), queryWrapper);
            return page;
        });
        try {
            Page<CmsCourse> cmsCoursePage = submit.get();
            res.add(new SubjectCourse("推荐", null, cmsCoursePage.getRecords()));
            res.add(new SubjectCourse(cmsClassifies.get(i).getName(), null, direction.get().getRecords()));
            res.add(new SubjectCourse("免费", null, free.get().getRecords()));
        } catch (Exception e) {
            return ApiResult.success(Collections.emptyList());
        }
        return ApiResult.success(res);
    }


    @GetMapping("/listCourseByType/{type}")
    public ApiResult<List<CmsCourse>> listCourseByType (@PathVariable Integer type) {
        return ApiResult.success(cmsCourseService.listCourseByTypeOrderActualOnShelfTimeDesc(type));
    }

    @GetMapping("/newCourses")
    public ApiResult<List<CmsCourse>>  newCourses () {
        return ApiResult.success(cmsCourseService.newCourses(8));
    }


}
