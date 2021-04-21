package cn.tinybee.ke.common.config;

import cn.tinybee.ke.common.config.properties.Aliyun;
import cn.tinybee.ke.common.config.properties.TinybeeVideoProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Classname ArcusProperties
 * @Description TODO
 * @Date 2020/6/5 13:23
 * @Created by hao.huang
 */
@Configuration
@EnableConfigurationProperties({TinybeeVideoProperties.class, Aliyun.class})
public class ArcusProperties {

}
