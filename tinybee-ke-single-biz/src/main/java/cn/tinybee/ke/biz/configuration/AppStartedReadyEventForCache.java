package cn.tinybee.ke.biz.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @author huanghao
 * @version 1.0
 * @description
 * @date 2021/3/19 15:09
 */
@Slf4j
@Component
public class AppStartedReadyEventForCache implements ApplicationListener<ApplicationReadyEvent> {

    private ConfigurationService configurationService;

    @Autowired
    public void setConfigurationService(ConfigurationService configurationService) {
        this.configurationService = configurationService;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        // 初始化配置缓存
        ConfigurableApplicationContext applicationContext = applicationReadyEvent.getApplicationContext();

        // 初始化云配置

        configurationService.initConfig();
    }
}
