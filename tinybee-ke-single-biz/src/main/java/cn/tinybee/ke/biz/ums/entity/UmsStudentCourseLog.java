package cn.tinybee.ke.biz.ums.entity;

import cn.tinybee.ke.common.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 会员学习日志表
 * </p>
 *
 * @author hao.huang
 * @since 2020-05-02
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_ums_student_course_log")
public class UmsStudentCourseLog extends BaseEntity<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 会员ID
     */
    private Long userId;

    /**
     * 内容ID
     */
    private Long courseId;

    /**
     * 章节ID
     */
    @TableField(exist = false)
    private Long chapterId;

    /**
     * 内容详情ID
     */
    private Long periodId;

    /**
     * 学习进度
     */
    private BigDecimal progress;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;


}
