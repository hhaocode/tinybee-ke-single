package cn.tinybee.ke.biz.ums.service.impl;

import cn.tinybee.ke.biz.ums.entity.UmsStudentCourseLog;
import cn.tinybee.ke.biz.ums.mapper.UmsStudentCourseLogMapper;
import cn.tinybee.ke.biz.ums.service.IUmsStudentCourseLogService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 会员学习日志表 服务实现类
 * </p>
 *
 * @author hao.huang
 * @since 2020-05-02
 */
@Slf4j
@Service
public class UmsStudentCourseLogServiceImpl extends ServiceImpl<UmsStudentCourseLogMapper, UmsStudentCourseLog> implements IUmsStudentCourseLogService {

    @Override
    public UmsStudentCourseLog getLogByPeriodIdAndUserId(Long courseId, Long userId, Long periodId) {
        QueryWrapper<UmsStudentCourseLog> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        queryWrapper.eq("period_id", periodId);
        UmsStudentCourseLog one = this.getOne(queryWrapper);
        if (one != null) {
            return one;
        }
        // 插入一条
        UmsStudentCourseLog courseLog = new UmsStudentCourseLog();
        courseLog.setCourseId(courseId);
        courseLog.setUserId(userId);
        courseLog.setPeriodId(periodId);
        courseLog.setProgress(BigDecimal.ZERO);
        courseLog.setCreateTime(LocalDateTime.now());
        try {
            this.save(courseLog);
            return courseLog;
        } catch (Exception e) {
            log.info("已有学习日志");
            return this.getOne(queryWrapper);
        }
    }

    @Override
    public Boolean changeLog(Long courseId, Long periodId, Long time, Long currentUserId) {
        if (time <= 0) {
            return false;
        }
        UmsStudentCourseLog logByPeriodIdAndUserId = this.getLogByPeriodIdAndUserId(courseId, currentUserId, periodId);
        // 获取时长
        logByPeriodIdAndUserId.setProgress(new BigDecimal(50));
        this.updateById(logByPeriodIdAndUserId);
        return true;
    }
}
