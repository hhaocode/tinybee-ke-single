package cn.tinybee.ke.admin.system.service.impl;

import cn.tinybee.ke.admin.system.dto.SystemDeptDto;
import cn.tinybee.ke.admin.system.entity.SystemDept;
import cn.tinybee.ke.admin.system.mapper.SystemDeptMapper;
import cn.tinybee.ke.admin.system.service.SystemDeptService;
import cn.tinybee.ke.common.entity.KeywordQueryParam;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * <p>
 * 组织表 服务实现类
 * </p>
 *
 * @author hao.huang
 * @since 2020-03-29
 */
@Service
public class SystemDeptServiceImpl extends ServiceImpl<SystemDeptMapper, SystemDept> implements SystemDeptService {
    @Autowired
    private Mapper mapper;

    public List<SystemDeptDto> tree() {
        QueryWrapper<SystemDept> param = new QueryWrapper<>();
        param.eq("deleted", false);
        return deptTree(this.list(param));
    }

    public List<SystemDeptDto> tableTree(KeywordQueryParam param) {
        return deptTree(this.list());
    }

    private List<SystemDeptDto> deptTree(List<SystemDept> list) {
        if (list.isEmpty()) {
            return Collections.emptyList();
        }
        Map<Long, List<SystemDeptDto>> pidMap = new HashMap<>();
        List<SystemDeptDto> resDataList = new ArrayList<>();
        list.forEach(v -> {
            Long pid = v.getPid();
            if (pid == null) {
                pid = 0L;
                v.setPid(pid);
            }

            List<SystemDeptDto> children = pidMap.get(pid);
            SystemDeptDto item = mapper.map(v, SystemDeptDto.class);
            if (children != null) {
                children.add(item);
            }else {
                children = new ArrayList<>();
                children.add(item);
                pidMap.put(pid, children);
            }
            resDataList.add(item);
        });
        List<SystemDeptDto> res = new ArrayList<>();
        resDataList.forEach( v -> {
            v.setChildren(pidMap.get(v.getId()));
            if (v.getPid() == 0) {
                res.add(v);
            }
        });
        return res;
    }
}
