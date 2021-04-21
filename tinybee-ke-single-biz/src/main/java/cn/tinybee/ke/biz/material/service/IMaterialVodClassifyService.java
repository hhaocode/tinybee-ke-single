package cn.tinybee.ke.biz.material.service;

import cn.tinybee.ke.biz.material.entity.MaterialVodClassify;
import cn.tinybee.ke.common.enums.VodType;
import cn.tinybee.ke.common.handle.tree.TreeNode;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hao.huang
 * @since 2020-10-23
 */
public interface IMaterialVodClassifyService extends IService<MaterialVodClassify> {

    List<TreeNode<MaterialVodClassify>> tree(VodType type);

    MaterialVodClassify saveOrUpdate(MaterialVodClassify materialVodClassify, Long currentUserId);
}
