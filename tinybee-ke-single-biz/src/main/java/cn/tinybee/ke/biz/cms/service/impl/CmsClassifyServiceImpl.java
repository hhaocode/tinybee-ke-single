package cn.tinybee.ke.biz.cms.service.impl;

import cn.tinybee.ke.biz.cms.entity.CmsClassify;
import cn.tinybee.ke.biz.cms.mapper.CmsClassifyMapper;
import cn.tinybee.ke.biz.cms.service.ICmsClassifyService;
import cn.tinybee.ke.common.entity.Operator;
import cn.tinybee.ke.common.handle.tree.TreeHandler;
import cn.tinybee.ke.common.handle.tree.TreeNode;
import cn.tinybee.ke.common.util.AssertUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

/**
 * <p>
 * 内容分类 服务实现类
 * </p>
 *
 * @author hao.huang
 * @since 2020-12-18
 */
@Service
public class CmsClassifyServiceImpl extends ServiceImpl<CmsClassifyMapper, CmsClassify> implements ICmsClassifyService {

    @Autowired
    private Mapper mapper;

    @Override
    public List<CmsClassify> listByType(Integer type, Boolean available) {
        QueryWrapper<CmsClassify> queryWrapper = new QueryWrapper<>();
        if (available != null) {
            queryWrapper.eq("available", available);
        }
        queryWrapper.eq("deleted", false);
        if (type != null) {
            queryWrapper.eq("type", type);
        }
        return this.list(queryWrapper);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean createOrModify(Operator operator, CmsClassify param) {
        check(param);
        CmsClassify db = null;
        AssertUtils.isFalse(param.getType() != CmsClassify.TYPE_DIRECTION && param.getType() != CmsClassify.TYPE_CLASSIFY, "类型错误");
        if (param.getId() != null) {
            db = this.getById(param.getId());
            AssertUtils.notNull(db, "无效的" + CmsClassify.TYPE_MESSAGE.get(param.getType()));
            db.setIcon(param.getIcon());
            db.setImgUrl(param.getImgUrl());
            db.setPid(param.getPid());
            db.setName(param.getName());
            db.setRemark(param.getRemark());
            db.setSort(param.getSort());
        } else {
            db = mapper.map(param, CmsClassify.class);
            db.setAvailable(true);
            db.setDeleted(false);
        }
        if (db.getPid() == null) {
            db.setPid(0L);
        }
        return this.saveOrUpdate(db);
    }

    @Override
    public List<CmsClassify> listChildByPid(Long pid, Boolean available) {
        QueryWrapper<CmsClassify> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("deleted", false);
        queryWrapper.ne("pid", pid);
        if (available != null) {
            queryWrapper.ne("available", available);
        }
        return this.list(queryWrapper);
    }

    @Override
    public List<TreeNode<CmsClassify>> tree(Boolean available) {
        List<TreeNode<CmsClassify>> tree = TreeHandler.tree(listByType(null, available));
        return tree;
    }

    @Override
    public List<CmsClassify> listByCourseIds(Collection<Long> courseIds) {
        return baseMapper.listByCourseIds(courseIds);
    }

    private void check (CmsClassify param) {
        QueryWrapper<CmsClassify> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("deleted", false);
        queryWrapper.eq("name", param.getName());
        if (param.getId() == null) {
            queryWrapper.ne("id", param.getId());
        }
        AssertUtils.isFalse(this.count(queryWrapper) > 0, String.format("名称重复[%s]", param.getName()));
    }

}
