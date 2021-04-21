package cn.tinybee.ke.common.config.properties;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author hao.huang
 * @version v1.0.0
 * @Package : cn.tinybee.ke.ke.common.config.properties
 * @Description : TODO
 * @Create on : 2020/7/10 11:22
 **/
@Data
@Configuration
@ConfigurationProperties(prefix = Aliyun.KEY)
@ToString
public class Aliyun {

    protected static final String KEY = "aliyun";

//    private String SignName = ""
    private String regionId;

    private String accessKeyId;
    private String accessKeySecret;

    private String endpoint = "oss-cn-shanghai.aliyuncs.com";

    private String bucketName;

    private Long cateId;

    private String workflowId;

    private String signName;

    private String templateCode;
}
