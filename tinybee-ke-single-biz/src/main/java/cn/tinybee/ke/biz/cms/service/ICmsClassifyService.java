package cn.tinybee.ke.biz.cms.service;

import cn.tinybee.ke.biz.cms.entity.CmsClassify;
import cn.tinybee.ke.common.entity.Operator;
import cn.tinybee.ke.common.handle.tree.TreeNode;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Collection;
import java.util.List;

/**
 * <p>
 * 内容分类 服务类
 * </p>
 *
 * @author hao.huang
 * @since 2020-12-18
 */
public interface ICmsClassifyService extends IService<CmsClassify> {

    List<CmsClassify> listByType(Integer type, Boolean available);

    boolean createOrModify(Operator operator, CmsClassify param);

    List<CmsClassify> listChildByPid (Long pid, Boolean available);

    List<TreeNode<CmsClassify>> tree(Boolean available);

    List<CmsClassify> listByCourseIds(Collection<Long> courseIds);
}
