package cn.tinybee.ke.admin.system.service;

import cn.tinybee.ke.admin.system.entity.SystemPermission;
import cn.tinybee.ke.admin.system.entity.SystemRole;
import cn.tinybee.ke.admin.system.entity.SystemRolePermission;
import cn.tinybee.ke.admin.system.mapper.SystemRoleMapper;
import cn.tinybee.ke.common.entity.IdIdsType;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author hao.huang
 * @since 2020-03-29
 */
@Service
public class SystemRoleService extends ServiceImpl<SystemRoleMapper, SystemRole> implements IService<SystemRole> {

    @Autowired
    private SystemRolePermissionService systemRolePermissionService;

    public List<SystemPermission> getRoleResources(Long roleId) {
        return baseMapper.getRolePermissions(roleId);
    }

    @Transactional
    public Boolean saveRoleResources(IdIdsType<Long, Long> roleResource, Long creator) {
        Long roleId = roleResource.getId();
        Map<String, Object> removeMapParam = new HashMap<>();
        removeMapParam.put("role_id", roleId);
        systemRolePermissionService.removeByMap(removeMapParam);
        List<SystemRolePermission> roleResources = roleResource.getIds().stream().map(v -> {
            SystemRolePermission entity = new SystemRolePermission();
            entity.setCreator(creator);
            entity.setCreateTime(LocalDateTime.now());
            entity.setRoleId(roleId);
            entity.setPermissionId(v);
            return entity;
        }).collect(Collectors.toList());
        return systemRolePermissionService.saveBatch(roleResources);
    }
}
