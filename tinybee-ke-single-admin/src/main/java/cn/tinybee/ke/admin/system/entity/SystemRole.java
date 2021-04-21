package cn.tinybee.ke.admin.system.entity;

import cn.tinybee.ke.common.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * <p>
 * 角色表
 * </p>
 *
 * @author hao.huang
 * @since 2020-03-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_system_role")
public class SystemRole extends BaseEntity<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 名称
     */
    private String name;

    /**
     * 名称
     */
    private String remark;

    /**
     * 权限代码
     */
    private String permissionCode;

    /**
     * 显示顺序
     */
    private Long sort;

    /**
     * 是否可用
     */
    private Boolean available;

    /**
     * 权限范围
     */
    private Integer dataScope;

    /**
     * 权限
     */
    private String deptIds;

    /**
     * 创建人
     */
    private Long creator;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;


}
