package cn.tinybee.ke.biz.sem.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import cn.tinybee.ke.common.base.BaseEntity;
import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author hao.huang
 * @since 2021-03-15
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_sem_price")
public class SemPrice extends BaseEntity<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 产品Id
     */
    private Long spuId;

    /**
     * 规格ID
     */
    private Long skuId;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 市场价
     */
    private BigDecimal markerPrice;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    private LocalDateTime modifyTime;

    /**
     * 是否删除
     */
    private Boolean deleted;


    private List<SemSku> skus;

}
