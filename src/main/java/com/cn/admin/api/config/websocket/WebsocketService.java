package com.cn.admin.api.config.websocket;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *@Author fengzhilong
 *@Date 2021/6/16 16:12
 *@Desc
 **/
@ServerEndpoint("/websocket/{ghid}")
@Component
public class WebsocketService {
    static Log log = LogFactory.get(WebsocketService.class);
    // 在线人数
    private static AtomicInteger onlineCnt = new AtomicInteger(0);
    //在线客户端
    private static Map<String, Session> websocketMap = new ConcurrentHashMap<>();

    private String ghid = "";

    //成功建立连接时
    @OnOpen
    public void OnOpen(Session session, @PathParam("ghid") String ghid) {
        //在线数加1
        onlineCnt.incrementAndGet();
        websocketMap.put(session.getId(), session);
        log.info("用户{}加入，当前在线人数：{}", ghid, onlineCnt.get());
    }

    // 关闭连接时
    @OnClose
    public void onClose(Session session, @PathParam("ghid") String ghid) {
        onlineCnt.decrementAndGet();
        websocketMap.remove(session.getId());
        log.info("用户{}退出，当前在线人数：{}", ghid, onlineCnt.get());
    }

    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
        log.info("服务器收到客户端[{}]消息：{}", session.getId(), message);
        this.sendMessage(message, session);
    }

    @OnError
    public void onError(Session session, Throwable error) {
        log.error("-----------------发生错误------------------");
        log.error(error.getMessage());
        error.printStackTrace();
    }

    public void sendMessage(String message, Session session) throws IOException {
        log.info("发送消息：{}", message);
        session.getBasicRemote().sendText(message);
    }


}
