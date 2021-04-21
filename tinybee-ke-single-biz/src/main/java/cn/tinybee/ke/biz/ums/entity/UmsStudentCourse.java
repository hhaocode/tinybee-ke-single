package cn.tinybee.ke.biz.ums.entity;

import cn.tinybee.ke.common.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 会员拥有内容表
 * </p>
 *
 * @author hao.huang
 * @since 2020-05-02
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_ums_student_course")
public class UmsStudentCourse extends BaseEntity<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 内容ID
     */
    private Long courseId;

    /**
     * 会员ID
     */
    private Long userId;

    /**
     * 是否可用
     */
    private Boolean available;

    /**
     * 已学习时长
     */
    private BigDecimal studyHours;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    private LocalDateTime modifyTime;

    private Boolean paid;

    private Long lastStudiedPeriodId;

    public void init (Boolean paid) {
        LocalDateTime now = LocalDateTime.now();
//        this.studyHours = BigDecimal.ZERO;
        this.available = true;
        this.createTime = now;
        this.modifyTime = now;
        this.paid = paid;
    }

}
