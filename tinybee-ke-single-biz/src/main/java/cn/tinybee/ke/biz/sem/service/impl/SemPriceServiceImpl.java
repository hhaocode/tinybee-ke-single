package cn.tinybee.ke.biz.sem.service.impl;

import cn.tinybee.ke.biz.sem.entity.SemPrice;
import cn.tinybee.ke.biz.sem.mapper.SemPriceMapper;
import cn.tinybee.ke.biz.sem.service.ISemPriceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hao.huang
 * @since 2021-03-15
 */
@Service
public class SemPriceServiceImpl extends ServiceImpl<SemPriceMapper, SemPrice> implements ISemPriceService {

    @Override
    public SemPrice saveOrModify(SemPrice param) {
        return null;
    }
}
