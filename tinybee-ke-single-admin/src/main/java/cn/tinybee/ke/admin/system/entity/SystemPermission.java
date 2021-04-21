package cn.tinybee.ke.admin.system.entity;

import cn.tinybee.ke.common.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * <p>
 * 资源表
 * </p>
 *
 * @author hao.huang
 * @since 2020-03-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_system_permission")
public class SystemPermission extends BaseEntity<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 上级ID
     */
    private Long pid;

    /**
     * 资源名称
     */
    @NotBlank(message = "资源名称不能为空")
    private String title;

    /**
     * 权限代码
     */
    @Length(max = 200)
    private String permissionCode;

    /**
     * 资源类型 1菜单 2 页面元素 3操作
     */
    private Integer type;

    /**
     * 资源路径
     */
//    @NotBlank(message = "资源路径不能为空")
    private String path;

    /**
     * 资源ICON
     */
    @Length(max = 100)
    private String icon;

    /**
     * 资源顺序
     */
    private Integer sort;

    /**
     * 是否可用
     */
    private Boolean available;


}
