package cn.tinybee.ke.common.pay.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * @author huanghao
 * @version 1.0
 * @description
 * @date 2020/11/9 14:16
 */
@Data
public class UniformOrder implements Serializable {

    private String subject;
    private String outTradeNo;
    private String totalAmount;

    private String quitUrl;
    private String returnUrl;
}
