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

/**
 * <p>
 * 学习路径
 * </p>
 *
 * @author hao.huang
 * @since 2021-03-14
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_cms_learning_path")
public class CmsLearningPath extends BaseEntity<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 标题
     */
    @NotBlank(message = "请输入标题")
    private String title;

    /**
     * 描述
     */
    @NotBlank(message = "请输入描述")
    private String description;

    /**
     * 图标
     */
    private String icon;

    /**
     * 封面
     */
    @NotBlank(message = "上传封面标题")
    private String coverUrl;

    /**
     * 总时长(小时)
     */
    private BigDecimal totalHour = BigDecimal.ZERO;

    /**
     * 序号
     */
    private Long sort = 0L;

    @TableField(exist = false)
    private List<CmsLearningPathStage> stages;

    @TableField(exist = false)
    private List<Long> courseIds;

    @TableField(exist = false)
    private List<CmsCourse> courses;

    @TableField(exist = false)
    private Long totalSubCount;//总订阅数
    @TableField(exist = false)
    private Integer stageCnt;
    @TableField(exist = false)
    private Integer courseCnt;

}
