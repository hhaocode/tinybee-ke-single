package cn.tinybee.ke.biz.cms.service;

import cn.tinybee.ke.biz.cms.entity.CmsLive;
import cn.tinybee.ke.biz.cms.entity.CmsPeriod;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hao.huang
 * @since 2021-01-25
 */
public interface ICmsLiveService extends IService<CmsLive> {

    CmsLive modifyPeriodLive(Long currentUserId, CmsPeriod cmsPeriod);


    CmsLive getPeriodLiveByPeriodId(Long periodId);


    Page<CmsLive> pageQuery(Page pageConvert, CmsLive param);
}
