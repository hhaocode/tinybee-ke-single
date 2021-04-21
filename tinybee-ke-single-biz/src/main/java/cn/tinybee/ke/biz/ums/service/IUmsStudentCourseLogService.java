package cn.tinybee.ke.biz.ums.service;

import cn.tinybee.ke.biz.ums.entity.UmsStudentCourseLog;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 会员学习日志表 服务类
 * </p>
 *
 * @author hao.huang
 * @since 2020-05-02
 */
public interface IUmsStudentCourseLogService extends IService<UmsStudentCourseLog> {

    UmsStudentCourseLog getLogByPeriodIdAndUserId(Long courseId, Long userId, Long periodId);

    Boolean changeLog(Long courseId, Long periodId, Long time, Long currentUserId);

}
