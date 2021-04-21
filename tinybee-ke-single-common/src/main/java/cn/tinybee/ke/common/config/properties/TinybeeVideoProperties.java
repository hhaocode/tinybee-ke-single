package cn.tinybee.ke.common.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Classname VideoProperties
 * @Description TODO
 * @Date 2020/6/5 13:18
 * @Created by hao.huang
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "tinybee.video")
public class TinybeeVideoProperties {


    private String uploadDir;

    private String m3u8Dir;

}
