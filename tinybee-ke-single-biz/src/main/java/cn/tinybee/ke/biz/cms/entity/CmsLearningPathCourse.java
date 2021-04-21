package cn.tinybee.ke.biz.cms.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import cn.tinybee.ke.common.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author hao.huang
 * @since 2021-03-14
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_cms_learning_path_course")
public class CmsLearningPathCourse extends BaseEntity<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 路径ID
     */
    private Long pathId;

    /**
     * 路径阶段ID
     */
    private Long pathStageId;

    /**
     * 课程ID
     */
    private Long courseId;

    /**
     * 序号
     */
    private Long sort = 0L;

    @TableField(exist = false)
    private CmsCourse cmsCourse;

}
