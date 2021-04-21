package cn.tinybee.ke.biz.sem.service.impl;

import cn.tinybee.ke.biz.sem.entity.SemActivity;
import cn.tinybee.ke.biz.sem.entity.SemActivityCourses;
import cn.tinybee.ke.biz.sem.mapper.SemActivityMapper;
import cn.tinybee.ke.biz.sem.service.ISemActivityCoursesService;
import cn.tinybee.ke.biz.sem.service.ISemActivityService;
import cn.tinybee.ke.common.entity.Operator;
import cn.tinybee.ke.common.util.AssertUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hao.huang
 * @since 2021-01-19
 */
@Service
public class SemActivityServiceImpl extends ServiceImpl<SemActivityMapper, SemActivity> implements ISemActivityService {

    @Autowired
    private ISemActivityCoursesService activityCoursesService;

    @Override
    public boolean saveOrModify(Operator operator, SemActivity param) {
        LocalDateTime now = LocalDateTime.now();
        if (param.getId() == null) {
            param.setCreateTime(now);
            param.setCreator(operator.getId());
            param.setDeleted(false);
        }
        if (param.getGroupUserCount() == null) {

        }
        this.saveOrUpdate(param);
        activityCoursesService.changeActivityCourses(operator, param.getId(), param.getCourses());
        // 处理
        return true;
    }

    @Override
    public Page<SemActivity> pageRecords(Operator operator, Page page) {
        QueryWrapper<SemActivity> queryWrapper = new QueryWrapper<>();
        Page<SemActivity> res = this.page(page, queryWrapper);
        if (res.getRecords().isEmpty()) {
            return res;
        }
        Set<Long> collect = res.getRecords().stream().map(v -> v.getId()).collect(Collectors.toSet());
//        List<SemActivity> semActivities = baseMapper.listActivityNoPage(collect);
//        res.setRecords(semActivities);
        // 将数据中的List处理一下
//        res.getRecords().
        Map<Long, List<SemActivityCourses>> longListMap = activityCoursesService.listCourseByActivityIds(collect).stream().collect(Collectors.groupingBy(SemActivityCourses::getActivityId));
        res.getRecords().forEach(v -> {
            List<SemActivityCourses> courses = longListMap.get(v.getId());
            if (courses == null) {
                courses = Collections.emptyList();
            }
            v.setCourses(courses);
        });
        return res;
    }

    @Override
    public SemActivity get(Long id) {
        SemActivity activity = this.getById(id);
        AssertUtils.notNull(activity, "不存在活动");
        AssertUtils.isFalse(activity.getDeleted(), "活动已被删除");
        List<SemActivityCourses> courses = activityCoursesService.listCourseByActivityId(id);
        activity.setCourses(courses);
        // 判断哪些已经在活动中了  需要挑拣出来
        return activity;
    }

    @Override
    public boolean delete(Operator operator, Long id) {
        // 判断是否在活动中  如果已经开始并且没有结束 不能删除
        SemActivity activity = this.getById(id);
        AssertUtils.notNull(activity, "活动不存在");
        AssertUtils.isFalse(activity.getDeleted(), "活动已被删除");
        LocalDateTime now = LocalDateTime.now();
        AssertUtils.isFalse(now.isAfter(activity.getStartTime()) && now.isBefore(activity.getEndTime()), "活动进行中，不能删除");
        activity.setDeleted(true);
        activity.setModifier(operator.getId());
        activity.setModifyTime(now);
        return this.updateById(activity);
    }
}
