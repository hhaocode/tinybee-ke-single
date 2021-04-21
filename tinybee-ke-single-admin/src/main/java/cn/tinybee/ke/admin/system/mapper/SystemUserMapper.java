package cn.tinybee.ke.admin.system.mapper;

import cn.tinybee.ke.admin.system.entity.SystemPermission;
import cn.tinybee.ke.admin.system.entity.SystemRole;
import cn.tinybee.ke.admin.system.entity.SystemUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author hao.huang
 * @description
 * @date 2020/3/26
 */
public interface SystemUserMapper extends BaseMapper<SystemUser> {

    List<SystemRole> getUserRoles(@Param("userId") Long userId);


    List<SystemPermission> getUserPermissions(@Param("userId") Long userId, @Param("types") List<Integer> types, @Param("available") Boolean available);
}
