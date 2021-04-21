package cn.tinybee.ke.biz.sem.service.impl;

import cn.tinybee.ke.biz.sem.entity.SemPayment;
import cn.tinybee.ke.biz.sem.mapper.SemPaymentMapper;
import cn.tinybee.ke.biz.sem.service.ISemPaymentService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 支付表 服务实现类
 * </p>
 *
 * @author hao.huang
 * @since 2020-11-09
 */
@Service
public class SemPaymentServiceImpl extends ServiceImpl<SemPaymentMapper, SemPayment> implements ISemPaymentService {

    @Override
    public SemPayment getPaymentByOrderAndType(Long orderId, int paymentType) {
        QueryWrapper<SemPayment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("order_id", orderId);
        queryWrapper.eq("payment_type", paymentType);
        queryWrapper.orderByDesc("create_time");
        return this.getOne(queryWrapper);
    }
}
