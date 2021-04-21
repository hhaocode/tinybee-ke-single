package cn.tinybee.ke.biz.sem.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import cn.tinybee.ke.common.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * <p>
 * 
 * </p>
 *
 * @author hao.huang
 * @since 2021-01-19
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_sem_activity_courses")
public class SemActivityCourses extends BaseEntity<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 活动ID
     */
    private Long activityId;

    /**
     * 课程Id
     */
    private Long courseId;

    /**
     * 是否删除
     */
    private Boolean delete;


    private BigDecimal activityPrice;

    @TableField(exist = false)
    private BigDecimal originPrice;
    @TableField(exist = false)
    private String title;
    @TableField(exist = false)
    private String coverUrl;

}
