package cn.tinybee.ke.biz.sem.entity;

import cn.tinybee.ke.biz.ums.entity.UmsStudent;
import cn.tinybee.ke.common.base.BaseEntity;
import cn.tinybee.ke.common.util.IdUtils;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 订单表
 * </p>
 *
 * @author hao.huang
 * @since 2020-11-09
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_sem_order")
public class SemOrder extends BaseEntity<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 订单号
     */
    private String orderNo;

    /**
     * 订单时间
     */
    private LocalDateTime orderTime;

    /**
     * 订单总额
     */
    private BigDecimal amount;

    /**
     * 实际付款
     */
    private BigDecimal actualAmount;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 类型 1 课程订单 2 商城订单
     */
    private Integer type;

    /**
     * 机构ID 若是商城  则表示店家ID
     */
    private Long shopId;

    /**
     * 邮费
     */
    private BigDecimal postage;

    /**
     * 订单状态 1 待支付 2 支付成功 3 支付失败 4 取消 5 删除 6 待发货 7 待收货 8 待评价 9 退款 10 完成退款 11 退款
     */
    private Integer orderStatus;

    /**
     * 是否删除 0 否 1是
     */
    private Boolean deleted;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime modifyTime;

    private Long creator;

    private Long modifier;

    @TableField(exist = false)
    private String subject = "小蜜蜂课堂课程";


    @TableField(exist = false)
    private List<SemOrderItem> orderItems;

    @TableField(exist = false)
    private UmsStudent user;

    public void setCreate () {
        LocalDateTime now = LocalDateTime.now();
        this.orderTime = now;
        this.createTime = now;
        this.modifyTime = now;
        this.deleted = false;
        this.orderStatus = 1;
        this.type = 1;
        this.orderNo = IdUtils.orderNo();
    }

}
