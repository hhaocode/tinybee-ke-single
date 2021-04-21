package cn.tinybee.ke.biz.cms.entity;

import cn.tinybee.ke.common.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * <p>
 * 推荐表
 * </p>
 *
 * @author hao.huang
 * @since 2020-05-02
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_cms_course_recommend")
public class CmsCourseRecommend extends BaseEntity<Long> {

    private static final long serialVersionUID = 1L;

    private String title;

    /**
     * 平台, h5 pc app
     */
    private String platform;

    /**
     * 推荐类型
     */
    private String recommendCode;

    /**
     * 记录状态，true有效 false无效
     */
    private Boolean available;

    /**
     * 创建人
     */
    private Integer creator;

    /**
     * 修改人
     */
    private Integer modifier;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    private LocalDateTime modifyTime;


}
