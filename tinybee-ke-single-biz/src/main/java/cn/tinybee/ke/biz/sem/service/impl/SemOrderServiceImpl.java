package cn.tinybee.ke.biz.sem.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.tinybee.ke.biz.cms.entity.CmsCourse;
import cn.tinybee.ke.biz.cms.service.ICmsCourseService;
import cn.tinybee.ke.biz.sem.dto.OrderPaymentDto;
import cn.tinybee.ke.biz.sem.dto.OrderSubmitDto;
import cn.tinybee.ke.biz.sem.dto.OrderSubmitDto.OrderItemSubmitDto;
import cn.tinybee.ke.biz.sem.entity.SemOrder;
import cn.tinybee.ke.biz.sem.entity.SemOrderItem;
import cn.tinybee.ke.biz.sem.entity.SemPayment;
import cn.tinybee.ke.biz.sem.mapper.SemOrderMapper;
import cn.tinybee.ke.biz.sem.service.ISemOrderItemService;
import cn.tinybee.ke.biz.sem.service.ISemOrderService;
import cn.tinybee.ke.biz.sem.service.ISemPaymentService;
import cn.tinybee.ke.biz.ums.entity.UmsStudentCourse;
import cn.tinybee.ke.biz.ums.service.IUmsStudentCourseService;
import cn.tinybee.ke.common.exception.BusinessException;
import cn.tinybee.ke.common.exception.OrderPaidException;
import cn.tinybee.ke.common.pay.domain.UniformOrder;
import cn.tinybee.ke.common.pay.service.AlipayService;
import cn.tinybee.ke.common.pay.service.WXPayService;
import cn.tinybee.ke.common.service.RedisService;
import cn.tinybee.ke.common.util.AssertUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 订单表 服务实现类
 * </p>
 *
 * @author hao.huang
 * @since 2020-11-09
 */
@Service
public class SemOrderServiceImpl extends ServiceImpl<SemOrderMapper, SemOrder> implements ISemOrderService {

    @Autowired
    private ICmsCourseService iCmsCourseService;

    @Autowired
    private ISemOrderItemService iSemOrderItemService;

    @Autowired
    private ISemPaymentService iSemPaymentService;

    @Autowired
    private AlipayService alipayService;

    @Autowired
    private WXPayService wxPayService;

    @Autowired
    private RedisService redisService;

    static final String ORDER_SUBMIT_PREFIX = "order:submit:%s";

    @Autowired
    private IUmsStudentCourseService iUmsStudentCourseService;


    @Override
    public OrderSubmitDto compute(OrderSubmitDto orderSubmitDto, Long userId) {
        List<Long> skuIds = orderSubmitDto.getSkuIds();
        if (CollectionUtil.isEmpty(skuIds)) {
            throw new BusinessException("无效的课程");
        }
        // TODO 计算优惠券
        List<OrderItemSubmitDto> skus = new ArrayList<>();
        orderSubmitDto.setSkus(skus);

        // 获取订单中已有的
        Map<Long, List<SemOrderItem>> dbOrderItem = iSemOrderItemService.listOrderItemBySkuIds(skuIds).stream().collect(Collectors.groupingBy(SemOrderItem::getSkuId));

        for (Long skuId : skuIds) {
            if (dbOrderItem.containsKey(skuId)) {
                throw new BusinessException("课程已有订单，请去订单中心支付");
            }
            UmsStudentCourse umsStudentCourse = iUmsStudentCourseService.memberContent(skuId, userId);
            if (umsStudentCourse != null) {
                throw new BusinessException("课程已有订单，不需要重新支付，请去学习");
            }
            // 查看未支付的订单中

            //
            CmsCourse cmsCourse = iCmsCourseService.getById(skuId);
            if (cmsCourse == null) {
                throw new BusinessException("其中有无效的课程");
            }
            OrderItemSubmitDto item = new OrderItemSubmitDto();
            item.setActualPrice(cmsCourse.getFree() ? BigDecimal.ZERO : cmsCourse.getOriginalPrice().multiply(BigDecimal.ONE));
            item.setPrice(item.getActualPrice());
            item.setSkuId(skuId);
            skus.add(item);
        }
        return orderSubmitDto;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public SemOrder submit(OrderSubmitDto orderSubmitDto, Long userId) {

        boolean exists = redisService.exists(String.format(ORDER_SUBMIT_PREFIX, orderSubmitDto.getToken()));
        if (exists) {
            throw new OrderPaidException("此订单正在处理，请稍后");
        }
        redisService.set(String.format(ORDER_SUBMIT_PREFIX, orderSubmitDto.getToken()), true,10);
        try {
            OrderSubmitDto compute = compute(orderSubmitDto, userId);
            List<OrderItemSubmitDto> skus = compute.getSkus();
            if (CollectionUtil.isEmpty(skus)) {
                throw new BusinessException("无效的课程");
            }
            SemOrder semOrder = new SemOrder();
            semOrder.setCreate();
            BigDecimal actualAmount = BigDecimal.ZERO;
            BigDecimal amount = BigDecimal.ZERO;
            List<SemOrderItem> items = new ArrayList<>();

            for (OrderItemSubmitDto sku : skus) {
                actualAmount = actualAmount.add(sku.getActualPrice());
                amount = amount.add(sku.getPrice());
            }

            semOrder.setAmount(amount);
            semOrder.setActualAmount(actualAmount);
            semOrder.setUserId(userId);
            this.save(semOrder);

            List<SemOrderItem> orderItems = new ArrayList<>();
            for (OrderItemSubmitDto item : skus) {
                SemOrderItem orderItem = new SemOrderItem();
                orderItem.setCreate(semOrder.getId());
                orderItem.setActualPrice(item.getActualPrice());
                orderItem.setPrice(item.getPrice());
                orderItem.setSkuId(item.getSkuId());
                orderItems.add(orderItem);
            }
            iSemOrderItemService.saveBatch(orderItems);
            semOrder.setOrderItems(orderItems);
            return semOrder;
        } finally {
            redisService.del(String.format(ORDER_SUBMIT_PREFIX, orderSubmitDto.getToken()));
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public SemPayment pay(OrderPaymentDto orderPayment, Long currentUserId) {
        // 幂等控制 分布式锁
//        redisService.set();
        // 查询订单
        SemOrder order = this.getById(orderPayment.getOrderId());
        AssertUtils.notNull(order, "订单不存");
        if (1 != order.getOrderStatus() && 3 != order.getOrderStatus()) {
            throw new OrderPaidException("该订单已支付,请去订单中心查看");
        }
        // 获取支付信息
        SemPayment semPayment = iSemPaymentService.getPaymentByOrderAndType(order.getId(), 1);
        if (semPayment == null) {
            // 没有支付信息 则新创建一根
            semPayment = new SemPayment();
        }
        semPayment.setCreate();
        semPayment.setAmount(order.getActualAmount());
        semPayment.setOrderId(order.getId());
        semPayment.setOrderNo(order.getOrderNo());
        semPayment.setPaymentWay(orderPayment.getPaymentWay());
        semPayment.setSubjectJson("快照信息"); //TODO
        semPayment.setUserId(currentUserId);
        semPayment.setPaymentTime(LocalDateTime.now());
        iSemPaymentService.saveOrUpdate(semPayment);
        //TODO
        switch (orderPayment.getPlatform()) {
            case PC:
                semPayment.setPaymentResult(payPC(order, orderPayment));
                break;
            case APP:

                break;
            case WAP:

                break;

            case WX_MP:

                break;

            default:
                throw new BusinessException("不存在的支付平台");
        }
        semPayment.setOpenPay(false);
        // TODO 暂时支付成功
        paySuccess(order.getOrderNo());
        return semPayment;
    }

    @Override
    public Page<SemOrder> pageUser(Page pageConvert, Long currentUserId) {
        QueryWrapper<SemOrder> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", currentUserId);
        Page<SemOrder> page = this.page(pageConvert, queryWrapper);

        Set<Long> orderIds = page.getRecords().stream().map(v -> v.getId()).collect(Collectors.toSet());
        if (!orderIds.isEmpty()) {
            Map<Long, List<SemOrderItem>> longListMap = iSemOrderItemService.listOrderItemByOrderIds(orderIds).stream().collect(Collectors.groupingBy(SemOrderItem::getOrderId));
            page.getRecords().forEach(v -> {
                List<SemOrderItem> orderItems = longListMap.get(v.getId());
                v.setOrderItems(orderItems == null ? Collections.emptyList() : orderItems);
            });
        }
        return page;
    }

    @Override
    public SemOrder getForPay(String orderNo, Long currentUserId) {
        QueryWrapper<SemOrder> queryWrapper = new QueryWrapper();
        queryWrapper.eq("order_no", orderNo);
        queryWrapper.eq("user_id", currentUserId);
        SemOrder order = this.getOne(queryWrapper);
        AssertUtils.notNull(order, "无效订单");
        AssertUtils.isEq(order.getOrderStatus(), 1, "订单不是待支付状态，不能支付");
        List<SemOrderItem> orderItems = iSemOrderItemService.listOrderItemByOrderIds(Arrays.asList(order.getId()));
        order.setOrderItems(orderItems);
        return order;
    }

    @Override
    public void paySuccess(String orderNo) {
        QueryWrapper<SemOrder> orderQueryWrapper = new QueryWrapper<>();
        orderQueryWrapper.eq("order_no", orderNo);
        SemOrder order = this.getOne(orderQueryWrapper);
        if (order == null) {
            return;
        }
        order.setOrderStatus(2);
        order.setModifyTime(LocalDateTime.now());
        this.updateById(order);
        SemPayment payment = iSemPaymentService.getPaymentByOrderAndType(order.getId(), 1);
        payment.setPaymentStatus(2);
        payment.setPaymentTime(LocalDateTime.now());
        payment.setModifyTime(LocalDateTime.now());
        iSemPaymentService.updateById(payment);
        QueryWrapper<SemOrderItem> orderItemQueryWrapper = new QueryWrapper<>();
        orderItemQueryWrapper.eq("order_id", order.getId());
        List<SemOrderItem> orderItems = iSemOrderItemService.list(orderItemQueryWrapper);
        for (SemOrderItem orderItem : orderItems) {
            iUmsStudentCourseService.join(orderItem.getSkuId(), order.getUserId(), true);
        }
    }

    private String payPC(SemOrder order,OrderPaymentDto orderPayment) {

        UniformOrder uniformOrder = new UniformOrder();
        uniformOrder.setOutTradeNo(orderPayment.getOrderNo());
        uniformOrder.setTotalAmount("0.01");
        uniformOrder.setSubject(order.getSubject());
        uniformOrder.setReturnUrl(orderPayment.getReturnUrl());
        uniformOrder.setQuitUrl(orderPayment.getReturnUrl());
        switch (orderPayment.getPaymentWay()) {
            case ALIPAY:
                return alipayService.uniformOrderForPage(uniformOrder);
            case WX:
//                wxPayService.un
            default:
                return "";
        }

    }
}
