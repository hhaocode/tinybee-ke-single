package cn.tinybee.ke.biz.base.entity;

import cn.tinybee.ke.common.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * <p>
 * 字典表
 * </p>
 *
 * @author hao.huang
 * @since 2020-08-17
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_base_dict")
public class BaseDict extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 字典代码
     */
    @NotBlank(message = "字典代码不能为空")
    private String dictCode;
    /**
     * 字典名称
     */
    @NotBlank(message = "字典名称不能为空")
    private String name;

    /**
     * 执行sql
     */
    private String srcSql;

    /**
     * 备注
     */
    private String remark;

    /**
     * 状态 true 可用 false 不可用
     */
    private Boolean available;

    /**
     * 创建人
     */
    private Long creator;

    /**
     * 更新人
     */
    private Long modifier;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    private LocalDateTime modifyTime;


}
