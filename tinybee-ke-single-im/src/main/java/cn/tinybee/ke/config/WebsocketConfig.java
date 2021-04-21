package cn.tinybee.ke.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @author huanghao
 * @version 1.0
 * @description
 * @date 2021/3/18 17:00
 */
@Configuration
public class WebsocketConfig {

    @Bean
    public ServerEndpointExporter serverEndpointExporter () {
        ServerEndpointExporter serverEndpointExporter = new ServerEndpointExporter();


//        serverEndpointExporter.

        return serverEndpointExporter;
    }

}
