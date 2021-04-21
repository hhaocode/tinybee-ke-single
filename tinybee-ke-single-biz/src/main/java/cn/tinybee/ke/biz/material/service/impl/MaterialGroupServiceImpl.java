package cn.tinybee.ke.biz.material.service.impl;

import cn.tinybee.ke.biz.material.dto.MaterialDto;
import cn.tinybee.ke.biz.material.entity.MaterialGroup;
import cn.tinybee.ke.biz.material.mapper.MaterialGroupMapper;
import cn.tinybee.ke.biz.material.service.IMaterialGroupService;
import cn.tinybee.ke.common.entity.Operator;
import cn.tinybee.ke.common.util.AssertUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hao.huang
 * @since 2020-12-23
 */
@Service
public class MaterialGroupServiceImpl extends ServiceImpl<MaterialGroupMapper, MaterialGroup> implements IMaterialGroupService {

    @Override
    public List<MaterialGroup> listByType(Integer type, boolean isContainAll) {
        QueryWrapper<MaterialGroup> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("type", type);
        queryWrapper.orderByAsc("sort");
        List<MaterialGroup> list = this.list(queryWrapper);
        List<MaterialGroup> res = buildAllAndNoGroup(type, list, isContainAll);
        return res;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean saveOrModify(Operator user, MaterialGroup param) {
        QueryWrapper<MaterialGroup> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("type", param.getType());
        queryWrapper.eq("name", param.getName());
        if (param.getId() != null) {
            queryWrapper.ne("id", param.getId());
        }
        int count = this.count(queryWrapper);
        AssertUtils.isFalse(count > 0, "此名称已经存在");
        LocalDateTime now = LocalDateTime.now();
        if (param.getId() == null) {
            param.setCreateTime(now);
            param.setCreator(user.getId());
        } else {
            MaterialGroup materialGroup = this.getById(param.getId());
            param.setCreator(materialGroup.getCreator());
            param.setModifyTime(materialGroup.getCreateTime());
        }
        param.setModifyTime(now);
        param.setModifier(user.getId());
        return this.saveOrUpdate(param);
    }

    @Override
    public Boolean deleteById(Operator operator, Long id) {
        // 将已经引用的设置为未分组 TODO
//        this.getById(id).
        return this.removeById(id);
    }

    private List<MaterialGroup> buildAllAndNoGroup (Integer type, List<MaterialGroup> list,boolean isContainAll) {
        List<MaterialGroup> res = new ArrayList<>();
        if (isContainAll) {
            MaterialGroup all = new MaterialGroup();
            all.setType(type);
            all.setName("全部");
            res.add(all);
        }
        MaterialGroup noGroup = new MaterialGroup();
        noGroup.setId(0L);
        noGroup.setType(type);
        noGroup.setName("未分组");
        res.add(noGroup);
        res.addAll(list);
        return res;
    }
}
