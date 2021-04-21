package cn.tinybee.ke.core.config;

import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import org.springframework.context.annotation.Configuration;

/**
 * @author huanghao
 * @version 1.0
 * @description
 * @date 2021/3/1 9:05
 */
@Configuration
@NacosPropertySource(dataId = "tinybee-ke-admin", autoRefreshed = true)
public class NacosConfig {
}
