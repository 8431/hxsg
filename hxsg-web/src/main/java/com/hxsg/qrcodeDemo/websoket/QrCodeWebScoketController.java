package com.hxsg.qrcodeDemo.websoket;




import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * 用户通讯聊天控制器类
 *
 * @author yyp
 *         2014年12月27日下午6:56:46
 */

@RequestMapping("qrcode")
public class QrCodeWebScoketController extends TextWebSocketHandler {
    private Logger logger = Logger.getLogger(QrCodeWebScoketController.class);
   public static final Map<String, WebSocketSession> mp = new HashMap<String, WebSocketSession>();
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) {
        try {
            String msg = message.getPayload();
            if(msg.equals("pdf")){
                for(String p:mp.keySet()){
                    WebSocketSession ws= null;
                    try {
                        ws = mp.get(p);
                        if(!session.equals(ws)){
                            ws.sendMessage(message);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }


        } catch (Exception e) {
            logger.error("webscoket登录验证异常loginWebScoketController" + e.getMessage());
        }
    }


    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {

        mp.put((new Date()).toString(),session);
        System.out.println("Connection open！");
    }
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
    }
    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        super.handleTransportError(session, exception);
    }


}
