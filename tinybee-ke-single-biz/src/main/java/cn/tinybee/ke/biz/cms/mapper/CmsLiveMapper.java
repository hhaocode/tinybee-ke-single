package cn.tinybee.ke.biz.cms.mapper;

import cn.tinybee.ke.biz.cms.entity.CmsLive;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author hao.huang
 * @since 2021-01-25
 */
public interface CmsLiveMapper extends BaseMapper<CmsLive> {

    Page<CmsLive> page(Page page, CmsLive param);
}
