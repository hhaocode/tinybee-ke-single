package cn.tinybee.ke.biz.cms.entity;

import cn.tinybee.ke.common.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 内容介绍
 * </p>
 *
 * @author hao.huang
 * @since 2020-04-10
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_cms_course_intro")
public class CmsCourseIntro extends BaseEntity<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 内容ID
     */
    private Long courseId;

    /**
     * 描述内容
     */
    private String description;

    /**
     * 创建着
     */
    private Long creator;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;


}
