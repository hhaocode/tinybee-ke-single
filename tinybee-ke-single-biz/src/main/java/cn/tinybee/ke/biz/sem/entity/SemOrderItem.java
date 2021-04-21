package cn.tinybee.ke.biz.sem.entity;

import cn.tinybee.ke.common.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 订单详情表
 * </p>
 *
 * @author hao.huang
 * @since 2020-11-09
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_sem_order_item")
public class SemOrderItem extends BaseEntity<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 订单ID
     */
    private Long orderId;

    /**
     * 课程则是课程ID 商城则是SKUID
     */
    private Long skuId;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 类型 1 课程订单 2 商城订单
     */
    private Integer type;

    /**
     * 实付
     */
    private BigDecimal actualPrice;

    /**
     * 数量
     */
    private Integer num;

    /**
     * 是否删除 0 1
     */
    private Boolean deleted;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    private LocalDateTime modifyTime;

    private Long creator;

    private Long modifier;


    public void setCreate(Long orderId) {
        this.orderId = orderId;
        this.createTime = LocalDateTime.now();
        this.deleted = false;
        this.num = 1;
        this.type = 1;
    }

    @TableField(exist = false)
    private String title;

    @TableField(exist = false)
    private String coverUrl;

}
