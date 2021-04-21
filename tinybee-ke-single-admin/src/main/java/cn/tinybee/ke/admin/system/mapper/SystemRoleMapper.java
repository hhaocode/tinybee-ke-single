package cn.tinybee.ke.admin.system.mapper;

import cn.tinybee.ke.admin.system.entity.SystemPermission;
import cn.tinybee.ke.admin.system.entity.SystemRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 角色表 Mapper 接口
 * </p>
 *
 * @author hao.huang
 * @since 2020-03-29
 */
public interface SystemRoleMapper extends BaseMapper<SystemRole> {

    List<SystemPermission> getRolePermissions(@Param("roleId") Long roleId);



}
