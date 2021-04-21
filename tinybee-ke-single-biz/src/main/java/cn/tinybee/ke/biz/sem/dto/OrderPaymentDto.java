package cn.tinybee.ke.biz.sem.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * @author huanghao
 * @version 1.0
 * @description
 * @date 2020/11/9 19:14
 */
@Data
public class OrderPaymentDto implements Serializable {
    private static final long serialVersionUID = -8340662404187369081L;

    private String token;

    private Long orderId;

    private String orderNo;

    @NotEmpty(message = "支付渠道不存在")
    private OrderPaymentDto.PaymentWay paymentWay; // 支付方式 1 微信 2支付宝

    @NotEmpty(message = "支付平台不能为空")
    private PaymentPlatform platform;

    private String returnUrl;


    public static enum PaymentPlatform {
        APP, PC, WAP, WX_MP;
    }
    
    public static enum PaymentWay {
        ALIPAY, WX
    }

}
