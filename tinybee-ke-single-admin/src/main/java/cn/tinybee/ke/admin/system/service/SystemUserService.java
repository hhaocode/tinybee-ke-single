package cn.tinybee.ke.admin.system.service;

import cn.tinybee.ke.admin.system.dto.CacheUserDto;
import cn.tinybee.ke.admin.system.dto.SystemPermissionDto;
import cn.tinybee.ke.admin.system.dto.UserPermissionParamDto;
import cn.tinybee.ke.admin.system.entity.*;
import cn.tinybee.ke.admin.system.mapper.SystemUserMapper;
import cn.tinybee.ke.common.entity.IdIdsType;
import cn.tinybee.ke.common.exception.BusinessException;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author hao.huang
 * @description
 * @date 2020/3/26
 */
@Service
public class SystemUserService extends ServiceImpl<SystemUserMapper, SystemUser> {

    @Autowired
    @Lazy
    private SystemEmpService systemEmpService;

    @Autowired
    private SystemUserRoleService systemUserRoleService;

    @Autowired
    private SystemPermissionService systemPermissionService;

    @Autowired
    private SystemUserPermissionService systemUserPermissionService;

    private static final String INIT_PASSWORD = "135781012";

    public SystemUser getAdministractorByUserName(String username) {
        QueryWrapper<SystemUser>  queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        return this.getOne(queryWrapper);
    }

    @Transactional
    public void updateLastLoginInfo(String loginIp, Long userId) {
        SystemUser user = new SystemUser();
        user.setId(userId);
        user.setLastLoginTime(LocalDateTime.now());
        this.updateById(user);
    }

    public boolean createUser(Long empId, Long userId) {
        QueryWrapper<SystemUser> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("emp_id", empId);
        if (this.count(userQueryWrapper) > 0 ){
            throw new BusinessException("用户已经存在");
        }

        SystemEmp emp = systemEmpService.getById(empId);
        if (emp == null ) {
            throw new BusinessException("员工不存在");
        }
        if (emp.getDeleted()) {
            throw new BusinessException("员工已被删除");
        }
        if (emp.getStatus() != 1) {
            throw new BusinessException("员工不是在职状态");
        }

        SystemUser user = new SystemUser();
        user.setEmpId(empId);
        user.setPassword(SecureUtil.md5(INIT_PASSWORD));
        user.setUsername(emp.getJobNo());
        user.setCreator(userId);
        user.setCreateTime(LocalDateTime.now());
        user.setAvailable(true);
        return this.save(user);
    }

    public List<SystemRole> getUserRoles(Long userId) {
        return baseMapper.getUserRoles(userId);
    }

    public Boolean saveUserRoles(UserPermissionParamDto userPermissionParam, Long creator) {
        Long userId = userPermissionParam.getId();
        Map<String, Object> removeMapParam = new HashMap<>();
        removeMapParam.put("user_id", userId);
        LocalDateTime now = LocalDateTime.now();
        systemUserRoleService.removeByMap(removeMapParam);
        List<SystemUserRole> roleResources = userPermissionParam.getRoleIds().stream().map(v -> {
            SystemUserRole entity = new SystemUserRole();
            entity.setCreator(creator);
            entity.setCreateTime(now);
            entity.setUserId(userId);
            entity.setRoleId(v);
            return entity;
        }).collect(Collectors.toList());
        systemUserRoleService.saveBatch(roleResources);

        systemUserPermissionService.removeByMap(removeMapParam);
        List<SystemUserPermission> userPermissions = userPermissionParam.getPermissionIds().stream().map(v -> {
            SystemUserPermission entity = new SystemUserPermission();
            entity.setCreator(creator);
            entity.setCreateTime(now);
            entity.setUserId(userId);
            entity.setPermissionId(v);
            return entity;
        }).collect(Collectors.toList());
        systemUserPermissionService.saveBatch(userPermissions);
        return true;
    }

    /**
     * 获取用户权限
     * @param user
     * @param type
     * @return
     */
    public List<SystemPermission> getUserPermissions(CacheUserDto user, List<Integer> types) {
        // 如果是超级管理员 则是所有的权限
        if (user.getSuperAdmin()) {
            return systemPermissionService.listByType(types, Boolean.TRUE);
        }
        return baseMapper.getUserPermissions(user.getId(), types, Boolean.TRUE);
    }
    public List<SystemPermissionDto> getUserPermissionTree(CacheUserDto user, List<Integer> types, Boolean available) {
        if (user.getSuperAdmin()) {
            return systemPermissionService.tree(types, available);
        }
        return systemPermissionService.permissionTree(baseMapper.getUserPermissions(user.getId(), types, available));
    }

    public Collection<SystemUserPermission> getPermissionIds(Long userId) {
        QueryWrapper<SystemUserPermission> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        return systemUserPermissionService.list(queryWrapper);

    }
}
