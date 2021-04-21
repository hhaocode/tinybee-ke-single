package cn.tinybee.ke.biz.sem.service;

import cn.tinybee.ke.biz.sem.entity.SemAdvertisement;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 广告信息 服务类
 * </p>
 *
 * @author hao.huang
 * @since 2020-11-04
 */
public interface ISemAdvertisementService extends IService<SemAdvertisement> {

    SemAdvertisement save(SemAdvertisement semAdvertisement, Long currentUserId);

    Boolean available(Long id, Boolean available, Long currentUserId);

}
