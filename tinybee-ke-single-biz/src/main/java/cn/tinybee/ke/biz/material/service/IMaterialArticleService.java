package cn.tinybee.ke.biz.material.service;

import cn.tinybee.ke.biz.material.entity.MaterialArticle;
import cn.tinybee.ke.common.entity.Operator;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hao.huang
 * @since 2020-12-23
 */
public interface IMaterialArticleService extends IService<MaterialArticle> {


    Page<MaterialArticle> page(Operator user, Page page);

    MaterialArticle saveOrModify(Operator operator, MaterialArticle param);

    boolean delete(Operator operator, Long id);

    Page<MaterialArticle> page(Operator user, Page page, String title);

}
