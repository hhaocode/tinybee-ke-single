package cn.tinybee.ke.biz.sem.service;

import cn.tinybee.ke.biz.sem.dto.OrderPaymentDto;
import cn.tinybee.ke.biz.sem.dto.OrderSubmitDto;
import cn.tinybee.ke.biz.sem.entity.SemOrder;
import cn.tinybee.ke.biz.sem.entity.SemPayment;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 订单表 服务类
 * </p>
 *
 * @author hao.huang
 * @since 2020-11-09
 */
public interface ISemOrderService extends IService<SemOrder> {

    OrderSubmitDto compute (OrderSubmitDto orderSubmitDto, Long userId);

    SemOrder submit(OrderSubmitDto orderSubmitDto, Long userId);

    SemPayment pay(OrderPaymentDto orderPayment, Long currentUserId);

    Page<SemOrder> pageUser(Page pageConvert, Long currentUserId);

    SemOrder getForPay(String orderNo, Long currentUserId);

    void paySuccess (String orderNo);

}
