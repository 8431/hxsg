package com.hxsg.pk.websoket;


import com.google.gson.Gson;
import com.hxsg.CommonUtil.FinalMap;
import com.hxsg.po.NewRole;
import com.hxsg.redis.RedisDaoService;
import com.hxsg.util.MapUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.SerializationUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * PK服务器webscoket通信
 * <p>
 * <p>
 * {'type':'pkWait','money':1000,'key':{'roleA':1000,'roleB':1000,'key':'dwjdhwdg233','level':'10,20'},'pkType':'1000'}
 */
@RequestMapping("pkServerTest")
public class WebSocketPkServer2 extends TextWebSocketHandler implements Serializable {
    private static Log logger = LogFactory.getLog(WebSocketPkServer2.class);
    @Autowired
    RedisDaoService redisdaoservice;
    @Autowired
    Gson gsonBean;
    public static final Map<String, Object> WEBSOCKETMAP = new ConcurrentHashMap<String, Object>();

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) {

        try {
            //初始化加载
            String msg = message.getPayload();
            System.out.println(msg);
            Map<String, Object> dataMap = gsonBean.fromJson(msg, Map.class);
            String type = (String) dataMap.get("type");
            if ("pkWait".equals(type)) {
                Map<String, Object> jsonDataMap = (Map<String, Object>) dataMap.get("jsonData");
                String uuidKey = (String) jsonDataMap.get("uuidKey");
                Integer roleId = null;
                roleId = (Integer) redisdaoservice.get(uuidKey);
                //加入wession维护
                FinalMap.PKROLESESSION.put(roleId.toString(), session);
            }
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        logger.debug("开启连接。。。。");
        super.afterConnectionEstablished(session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        super.afterConnectionClosed(session, status);
        logger.debug("连接关闭。。。。");
        //从reids移除
        MapUtil.remove(FinalMap.PKROLESESSION, session);
    }


}