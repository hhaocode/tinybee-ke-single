package cn.tinybee.ke.admin.system.entity;

import cn.tinybee.ke.common.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 组织表
 * </p>
 *
 * @author hao.huang
 * @since 2020-03-29
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_system_dept")
public class SystemDept extends BaseEntity<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 上级ID
     */
    private Long pid;

    /**
     * 名称
     */
    private String name;

    /**
     * 简称
     */
    private String shortName;

    /**
     * 顺序
     */
    private Long sort;
    /**
     * 是否删除
     */
    private Boolean deleted;


}
