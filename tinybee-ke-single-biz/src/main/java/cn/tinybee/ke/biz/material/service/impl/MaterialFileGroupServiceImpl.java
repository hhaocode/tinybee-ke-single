package cn.tinybee.ke.biz.material.service.impl;

import cn.tinybee.ke.biz.material.entity.MaterialFile;
import cn.tinybee.ke.biz.material.entity.MaterialFileGroup;
import cn.tinybee.ke.biz.material.mapper.MaterialFileGroupMapper;
import cn.tinybee.ke.biz.material.service.IMaterialFileGroupService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hao.huang
 * @since 2020-10-23
 */
@Service
public class MaterialFileGroupServiceImpl extends ServiceImpl<MaterialFileGroupMapper, MaterialFileGroup> implements IMaterialFileGroupService {

    @Override
    public List<MaterialFileGroup> list(int type) {
        QueryWrapper<MaterialFileGroup> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("type", type);
//        queryWrapper.eq("type", type);
        return this.list(queryWrapper);
    }
}
