package cn.tinybee.ke.biz.cms.service.impl;

import cn.tinybee.ke.biz.cms.entity.CmsLive;
import cn.tinybee.ke.biz.cms.entity.CmsPeriod;
import cn.tinybee.ke.biz.cms.mapper.CmsLiveMapper;
import cn.tinybee.ke.biz.cms.service.ICmsLiveService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hao.huang
 * @since 2021-01-25
 */
@Service
public class CmsLiveServiceImpl extends ServiceImpl<CmsLiveMapper, CmsLive> implements ICmsLiveService {

    @Override
    public CmsLive modifyPeriodLive(Long currentUserId, CmsPeriod cmsPeriod) {
        CmsLive live = getPeriodLiveByPeriodId(cmsPeriod.getId());
        LocalDateTime now = LocalDateTime.now();
        if (live == null) {
            live = new CmsLive();
            live.setCreateTime(now);
            live.setCreator(currentUserId);
            live.setDeleted(false);
            live.setHasTranscoding(false);
        } else {
            live.setDeleted(false);
        }
        live.setCoverUrl(cmsPeriod.getCoverUrl());
        live.setPeriodId(cmsPeriod.getId());
        live.setTitle(cmsPeriod.getTitle());
        live.setSubtitle(cmsPeriod.getSubtitle());
        live.setType(CmsLive.LIVE_TYPE_COURSE);
        if (live.getStatus() == CmsLive.WAIT_LIVE_STATUS) {
            live.setStartTime(cmsPeriod.getLiveStartTime());
            live.setEndTime(cmsPeriod.getLiveEndTime());
        }
        live.setModifier(currentUserId);
        live.setModifyTime(now);
        this.saveOrUpdate(live);
        return live;
    }

    @Override
    public CmsLive getPeriodLiveByPeriodId(Long periodId) {
        QueryWrapper<CmsLive> queryWrapper = new QueryWrapper<>();
        return this.getOne(queryWrapper);
    }

    @Override
    public Page<CmsLive> pageQuery(Page pageConvert, CmsLive param) {

        Page<CmsLive> res = baseMapper.page(pageConvert, param);

        return res;
    }
}
