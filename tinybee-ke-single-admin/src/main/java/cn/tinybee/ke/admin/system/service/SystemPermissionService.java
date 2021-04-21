package cn.tinybee.ke.admin.system.service;

import cn.tinybee.ke.admin.system.dto.SystemPermissionDto;
import cn.tinybee.ke.admin.system.entity.SystemPermission;
import cn.tinybee.ke.admin.system.mapper.SystemPermissionMapper;
import cn.tinybee.ke.common.entity.KeywordQueryParam;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author hao.huang
 * @description
 * @date 2020/3/27
 */
@Service
public class SystemPermissionService extends ServiceImpl<SystemPermissionMapper, SystemPermission> {

    @Autowired
    private Mapper mapper;

    public List<SystemPermissionDto> tree(List<Integer> types, Boolean available) {
        return permissionTree(this.listByType(types, available));
    }

    public List<SystemPermission> listByType(List<Integer> types, Boolean available) {
        QueryWrapper<SystemPermission> param = new QueryWrapper<>();
        if (types != null) {
            param.in("type", types);
        }
        if (available != null) {
            param.eq("available", available);
        }
        param.orderByAsc("sort");
        param.orderByAsc("id");
        return this.list(param);
    }

    public List<SystemPermissionDto> tableTree(KeywordQueryParam param) {
        QueryWrapper<SystemPermission> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("sort");
        queryWrapper.orderByAsc("id");
        return permissionTree(this.list(queryWrapper));
    }

    public List<SystemPermissionDto> permissionTree(List<SystemPermission> list) {
        if (list.isEmpty()) {
            return Collections.emptyList();
        }
        Map<Long, List<SystemPermissionDto>> pidMap = new HashMap<>();
        List<SystemPermissionDto> resDataList = new ArrayList<>();
        list.forEach(v -> {
            Long pid = v.getPid();
            if (pid == null) {
                pid = 0L;
                v.setPid(pid);
            }

            List<SystemPermissionDto> children = pidMap.get(pid);
            SystemPermissionDto item = mapper.map(v, SystemPermissionDto.class);
            if (children == null) {
                children = new ArrayList<>();
                children.add(item);
                pidMap.put(pid, children);
            } else {
                children.add(item);
            }
            resDataList.add(item);
        });
        List<SystemPermissionDto> res = new ArrayList<>();
        resDataList.forEach( v -> {
            v.setChildren(pidMap.get(v.getId()));
            if (v.getPid() == 0) {
                res.add(v);
            }
        });
        return res;
    }
}
