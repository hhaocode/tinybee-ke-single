package cn.tinybee.ke;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author huanghao
 * @version 1.0
 * @description
 * @date 2021/3/18 16:47
 */
@SpringBootApplication
public class IMApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(IMApplication.class);
        run.registerShutdownHook();
    }

}
