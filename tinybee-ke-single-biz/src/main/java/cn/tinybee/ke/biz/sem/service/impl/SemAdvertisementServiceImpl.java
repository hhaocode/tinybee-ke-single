package cn.tinybee.ke.biz.sem.service.impl;

import cn.tinybee.ke.biz.sem.entity.SemAdvertisement;
import cn.tinybee.ke.biz.sem.mapper.SemAdvertisementMapper;
import cn.tinybee.ke.biz.sem.service.ISemAdvertisementService;
import cn.tinybee.ke.common.exception.BusinessException;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * <p>
 * 广告信息 服务实现类
 * </p>
 *
 * @author hao.huang
 * @since 2020-11-04
 */
@Service
public class SemAdvertisementServiceImpl extends ServiceImpl<SemAdvertisementMapper, SemAdvertisement> implements ISemAdvertisementService {

    @Transactional(rollbackFor = Exception.class)
    @Override
    public SemAdvertisement save(SemAdvertisement semAdvertisement, Long currentUserId) {
        if (semAdvertisement.getId() == null) {
            semAdvertisement.setCreateTime(LocalDateTime.now());
            semAdvertisement.setCreator(currentUserId);
        }
        semAdvertisement.setModifier(currentUserId);
        semAdvertisement.setModifyTime(LocalDateTime.now());
        this.saveOrUpdate(semAdvertisement);
        return semAdvertisement;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean available(Long id, Boolean available, Long currentUserId) {
        SemAdvertisement advertisement = this.getById(id);
        if (advertisement == null) {
            throw new BusinessException("该广告不存在");
        }
        advertisement.setAvailable(available);
        advertisement.setModifyTime(LocalDateTime.now());
        advertisement.setModifier(currentUserId);
        return this.updateById(advertisement);
    }
}
