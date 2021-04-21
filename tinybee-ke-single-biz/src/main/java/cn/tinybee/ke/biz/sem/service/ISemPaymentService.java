package cn.tinybee.ke.biz.sem.service;

import cn.tinybee.ke.biz.sem.entity.SemPayment;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 支付表 服务类
 * </p>
 *
 * @author hao.huang
 * @since 2020-11-09
 */
public interface ISemPaymentService extends IService<SemPayment> {


    SemPayment getPaymentByOrderAndType(Long orderId, int paymentType);
}
