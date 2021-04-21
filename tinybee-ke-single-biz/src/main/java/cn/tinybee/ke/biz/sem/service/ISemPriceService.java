package cn.tinybee.ke.biz.sem.service;

import cn.tinybee.ke.biz.sem.entity.SemPrice;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hao.huang
 * @since 2021-03-15
 */
public interface ISemPriceService extends IService<SemPrice> {


    SemPrice saveOrModify (SemPrice param);

}
