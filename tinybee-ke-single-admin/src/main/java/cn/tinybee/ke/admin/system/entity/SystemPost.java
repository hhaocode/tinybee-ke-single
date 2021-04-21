package cn.tinybee.ke.admin.system.entity;

import cn.tinybee.ke.common.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * <p>
 * 岗位表
 * </p>
 *
 * @author hao.huang
 * @since 2020-03-30
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_system_post")
public class SystemPost extends BaseEntity<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 岗位名称
     */
    private String name;

    /**
     * 岗位代码
     */
    private String code;

    /**
     * 排序
     */
    private Long sort;

    /**
     * 备注
     */
    private String remark;

    /**
     * 状态
     */
    private Boolean available;

    /**
     * 创建人
     */
    private Long creator;

    /**
     * 删除
     */
    private Boolean deleted;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;


}
