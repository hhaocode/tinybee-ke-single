package cn.tinybee.ke;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author hao.huang
 * @description
 * @date 2020/3/25
 */
@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class AdminApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(AdminApplication.class, args);
        context.registerShutdownHook();
    }
}
