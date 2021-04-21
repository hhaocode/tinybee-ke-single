package cn.tinybee.ke.biz.sem.entity;

import cn.tinybee.ke.biz.sem.dto.OrderPaymentDto;
import cn.tinybee.ke.common.base.BaseEntity;
import cn.tinybee.ke.common.util.IdUtils;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 支付表
 * </p>
 *
 * @author hao.huang
 * @since 2020-11-09
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_sem_payment")
public class SemPayment extends BaseEntity<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 支付流水
     */
    private String paymentNo;

    /**
     * 金额
     */
    private BigDecimal amount;

    /**
     * 支付状态 1 初始化 2 支付成功 3支付失败
     */
    private Integer paymentStatus;

    /**
     * 支付类型 1 付款  2退款
     */
    private Integer paymentType;

    /**
     * 支付方式 1 微信 2支付宝
     */
    private OrderPaymentDto.PaymentWay paymentWay;

    /**
     * 订单ID
     */
    private Long orderId;

    /**
     * 订单号
     */
    private String orderNo;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 支付时间
     */
    private LocalDateTime paymentTime;

    /**
     * 支付信息
     */
    private String subjectJson;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime modifyTime;

    @TableField(exist = false)
    private String paymentResult;

    @TableField(exist = false)
    private boolean isOpenPay;


    public void setCreate () {
        LocalDateTime now = LocalDateTime.now();
        this.modifyTime = now;
        this.createTime = now;
        this.paymentType = 1;
        this.paymentStatus = 1;
        this.paymentNo = IdUtils.paymentNo();
    }

}
