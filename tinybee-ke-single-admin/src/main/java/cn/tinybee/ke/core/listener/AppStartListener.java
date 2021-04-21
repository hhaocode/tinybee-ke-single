package cn.tinybee.ke.core.listener;

import cn.tinybee.ke.common.config.properties.Aliyun;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author huanghao
 * @version 1.0
 * @description
 * @date 2021/3/1 8:33
 */
@Slf4j
@Component
public class AppStartListener implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    private Aliyun aliyun;

    /**
     *  读取配置
     * @param applicationReadyEvent
     */
    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {

        log.info("项目启动成功");

        log.info("aliyun:{}", aliyun);
    }
}
