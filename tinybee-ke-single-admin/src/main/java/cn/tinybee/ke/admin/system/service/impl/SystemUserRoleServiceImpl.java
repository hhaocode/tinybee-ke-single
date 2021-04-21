package cn.tinybee.ke.admin.system.service.impl;

import cn.tinybee.ke.admin.system.entity.SystemUserRole;
import cn.tinybee.ke.admin.system.mapper.SystemUserRoleMapper;
import cn.tinybee.ke.admin.system.service.SystemUserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户与角色关联表 服务实现类
 * </p>
 *
 * @author hao.huang
 * @since 2020-03-30
 */
@Service
public class SystemUserRoleServiceImpl extends ServiceImpl<SystemUserRoleMapper, SystemUserRole> implements SystemUserRoleService {

}
