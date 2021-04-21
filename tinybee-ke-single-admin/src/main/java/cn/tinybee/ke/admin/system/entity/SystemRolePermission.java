package cn.tinybee.ke.admin.system.entity;

import cn.tinybee.ke.common.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 角色与资源关联表
 * </p>
 *
 * @author hao.huang
 * @since 2020-03-30
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_system_role_permission")
public class SystemRolePermission extends BaseEntity<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 角色ID
     */
    private Long roleId;

    /**
     * 资源ID
     */
    private Long permissionId;

    /**
     * 创建人员
     */
    private Long creator;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;


}
