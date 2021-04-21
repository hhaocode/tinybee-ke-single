package cn.tinybee.ke.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author huanghao
 * @version 1.0
 * @description
 * @date 2021/3/18 17:03
 */
@ServerEndpoint("/imserver/{userId}")
@Component
public class WebSocketServer {

    private static final Logger LOG = LoggerFactory.getLogger(WebSocketServer.class);

    private static volatile AtomicInteger ONLINE_COUNT = new AtomicInteger(0);

    private static volatile ConcurrentHashMap<String,WebSocketServer> webSocketMap = new ConcurrentHashMap<>();

    private String userId = ""; // 用户ID

    /**
     *
     * onOpen
     * onClose
     * onMessage
     * onError
     *
     */
    @OnOpen
    public void onOpen (Session session, @PathParam("userId") String userId) {

    }

    @OnClose
    public void onClose () {

    }

    public static int getOnlineCount() {
        return ONLINE_COUNT.get();
    }

}
