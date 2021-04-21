package cn.tinybee.ke;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author hao.huang
 * @description
 * @date 2020/3/29
 */
@SpringBootApplication
public class PortalApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(PortalApplication.class, args);
        context.registerShutdownHook();
    }

}
