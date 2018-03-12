package com.hxsg.pk.websoket;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dlf on 2018/2/2 0002.
 * Email 1429264916@qq.com
 */
@RequestMapping("chat")
public class ChatWebscoket extends TextWebSocketHandler {
    private static Log logger = LogFactory.getLog(WebSocketPkServer.class);

    public final static List<WebSocketSession> pkServerSession = new ArrayList<>();


    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        try {
            for(WebSocketSession s:pkServerSession){
                try{
                    s.sendMessage(message);
                }catch (Exception e){

                }

            }


        } catch (Exception e) {
            logger.error("连接pkServer异常:" + e.getMessage(), e);
        }
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        super.afterConnectionEstablished(session);
        pkServerSession.add(session);
    }
}
