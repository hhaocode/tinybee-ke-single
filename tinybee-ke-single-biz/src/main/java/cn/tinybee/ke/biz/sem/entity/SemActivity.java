package cn.tinybee.ke.biz.sem.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import cn.tinybee.ke.common.base.BaseEntity;
import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
@TableName("t_sem_activity")
public class SemActivity extends BaseEntity<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 1 秒杀  2 拼团
     */
    @NotNull(message = "请选择活动类型")
    private Integer type;

    /**
     * 标题
     */
    @NotBlank(message = "请输入活动标题")
    private String title;

    /**
     * 描述
     */
    private String description;

    /**
     * 开始时间
     */
    @NotNull(message = "请选择开始时间")
    private LocalDateTime startTime;

    /**
     * 结束时间
     */
    @NotNull(message = "请选择结束时间")
    private LocalDateTime endTime;

    /**
     * 成团人数
     */
    private Integer groupUserCount;

    /**
     * 创建人
     */
    private Long creator;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    private LocalDateTime modifyTime;

    /**
     * 修改人
     */
    private Long modifier;

    /**
     * 受否删除
     */
    private Boolean deleted;

    @TableField(exist = false)
    private List<SemActivityCourses> courses;

}
