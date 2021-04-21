package cn.tinybee.ke.biz.material.service.impl;

import cn.tinybee.ke.biz.material.entity.MaterialVodClassify;
import cn.tinybee.ke.biz.material.mapper.MaterialVodClassifyMapper;
import cn.tinybee.ke.biz.material.service.IMaterialVodClassifyService;
import cn.tinybee.ke.common.enums.VodType;
import cn.tinybee.ke.common.handle.tree.TreeHandler;
import cn.tinybee.ke.common.handle.tree.TreeNode;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
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
public class MaterialVodClassifyServiceImpl extends ServiceImpl<MaterialVodClassifyMapper, MaterialVodClassify> implements IMaterialVodClassifyService {

    @Override
    public List<TreeNode<MaterialVodClassify>> tree(VodType type) {
        QueryWrapper<MaterialVodClassify> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("type", type);
        List<MaterialVodClassify> list = this.list(queryWrapper);
        return TreeHandler.tree(list);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public MaterialVodClassify saveOrUpdate(MaterialVodClassify materialVodClassify, Long currentUserId) {
        if (materialVodClassify.getPid() == null) {
            materialVodClassify.setPid(0L);
        }
        LocalDateTime now = LocalDateTime.now();
        if (materialVodClassify.getId() == null ) {
            materialVodClassify.setCreateTime(now);
            materialVodClassify.setCreator(currentUserId);
        }
        materialVodClassify.setModifier(currentUserId);
        materialVodClassify.setModifyTime(now);
        this.saveOrUpdate(materialVodClassify);
        return materialVodClassify;
    }
}
