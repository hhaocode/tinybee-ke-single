package cn.tinybee.ke.biz.cms.service;

import cn.tinybee.ke.biz.cms.entity.CmsResource;
import cn.tinybee.ke.common.entity.Operator;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hao.huang
 * @since 2020-12-21
 */
public interface ICmsResourceService extends IService<CmsResource> {

    CmsResource saveOrModify(Operator operator, CmsResource param);


    Page<CmsResource> pageResource(Page pageConvert, Integer type);

    CmsResource get(Long id);

}
