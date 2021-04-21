package cn.tinybee.ke.biz.sem.service.impl;

import cn.tinybee.ke.biz.sem.entity.SemOrderItem;
import cn.tinybee.ke.biz.sem.mapper.SemOrderItemMapper;
import cn.tinybee.ke.biz.sem.service.ISemOrderItemService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

/**
 * <p>
 * 订单详情表 服务实现类
 * </p>
 *
 * @author hao.huang
 * @since 2020-11-09
 */
@Service
public class SemOrderItemServiceImpl extends ServiceImpl<SemOrderItemMapper, SemOrderItem> implements ISemOrderItemService {

    @Override
    public List<SemOrderItem> listOrderItemBySkuIds(Collection<Long> skuIds) {
        return baseMapper.listOrderItemBySkuIds(skuIds);
    }

    @Override
    public List<SemOrderItem> listOrderItemByOrderIds(Collection<Long> orderIds) {
        return baseMapper.listOrderItemByOrderIds(orderIds);
    }
}
