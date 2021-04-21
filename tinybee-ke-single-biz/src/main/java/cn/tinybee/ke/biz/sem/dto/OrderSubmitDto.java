package cn.tinybee.ke.biz.sem.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author huanghao
 * @version 1.0
 * @description
 * @date 2020/11/9 18:02
 */
@Data
public class OrderSubmitDto implements Serializable {

    private static final long serialVersionUID = 637633490634344997L;

    private String token;

    private List<OrderItemSubmitDto> skus;

    private List<Long> skuIds;

    @Data
    public static class OrderItemSubmitDto implements Serializable {

        private static final long serialVersionUID = -8053257600834013648L;

        private Long skuId;

        private BigDecimal price;

        private BigDecimal actualPrice;

    }

}
