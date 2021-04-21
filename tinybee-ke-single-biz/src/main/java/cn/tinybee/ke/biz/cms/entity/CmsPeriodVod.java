package cn.tinybee.ke.biz.cms.entity;

import cn.tinybee.ke.common.base.BaseEntity;
import cn.tinybee.ke.common.enums.VodType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author hao.huang
 * @since 2020-10-26
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_cms_period_vod")
public class CmsPeriodVod extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private String title;
    /**
     * 课程ID
     */
    private Long courseId;

    /**
     * 章节ID
     */
    private Long chapterId;

    /**
     * 课时ID
     */
    private Long periodId;

    /**
     * 音视频ID
     */
    private Long vodId;

    /**
     * 有效1 无效0
     */
    private Boolean available;

    /**
     * 上传人员
     */
    private Long creator;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改人员
     */
    private Long modifier;

    /**
     * 更新时间
     */
    private LocalDateTime modifyTime;

    @TableField(exist = false)
    private VodType vodType;

    @TableField(exist = false)
    private String vodName;

    @TableField(exist = false)
    private String vodTitle;

}
