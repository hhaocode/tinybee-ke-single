package cn.tinybee.ke.biz.cms.entity;

import java.math.BigDecimal;
import java.util.List;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import cn.tinybee.ke.common.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * 学习路径阶段
 * </p>
 *
 * @author hao.huang
 * @since 2021-03-14
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_cms_learning_path_stage")
public class CmsLearningPathStage extends BaseEntity<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 路径ID
     */
    @NotNull(message = "请选择学习路径")
    private Long pathId;

    /**
     * 名称
     */
    @NotBlank(message = "请输入名称")
    private String name;

    /**
     * 描述
     */
    private String description;

    /**
     * 总时长
     */
    private BigDecimal totalHour = BigDecimal.ZERO;

    /**
     * 序号
     */
    private Long sort = 0L;

    @TableField(exist = false)
    private List<Long> courseIds;

    @TableField(exist = false)
    private List<CmsCourse> courses;

    @TableField(exist = false)
    private List<CmsLearningPathCourse> pathCourses;

}
