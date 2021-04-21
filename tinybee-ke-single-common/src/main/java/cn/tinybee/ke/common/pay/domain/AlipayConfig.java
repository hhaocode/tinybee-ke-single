package cn.tinybee.ke.common.pay.domain;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author huanghao
 * @version 1.0
 * @description
 * @date 2020/11/9 14:00
 */

@Data
@Configuration
@ConfigurationProperties(prefix = AlipayConfig.KEY)
public class AlipayConfig {

    protected static final String KEY = "pay.ali";

    private String appId; // 2021001199670364

    private String merchantPrivateKey; //

    private String alipayPublicKey;

    private String notifyUrl;

}
