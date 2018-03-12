package com.hxsg.pk.websoket.impl;



import com.google.gson.Gson;
import com.hxsg.pk.websoket.PkServerDao;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.Map;


/**
 * PK服务器实现类
 */

public abstract  class PkServerDaoImpl extends TextWebSocketHandler implements PkServerDao{
    private static Log logger = LogFactory.getLog(PkServerDaoImpl.class);
    private Map<String, Object> pkmap=null;
    @Override
    public void loading() {

    }
    /**
     * 1.A发起pk初始化话A数据并等待B加入
     */
    @Override
    public void proA() {

    }

    @Override
    public void proB() {

    }

    @Override
    public void proData() {

    }

    @Override
    public void excute() {
        /**
         *   1.A发起pk初始化话A数据并等待B加入
         *   2.B加入队列，根据B的类型（玩家，野怪）初始化B数据
         *   3.下发AB数据到AB客户端
         *   4.等待AB客户端发送攻击指令（默认指令）
         *   5.AB发送指令到服务器，服务器对指令进行计算，如果有一方阵亡 PK结束并进行奖励发放，以及结束信息推送
         *   否则继续执行第3步 一直循环
         */
        String type = (String) pkmap.get("type");
        switch (type){
            
        }
        out:
        while(true){
            proA();
            proB();
            proData();
        }
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        //初始化加载
        String msg = message.getPayload();
        Gson gn = new Gson();
        pkmap = gn.fromJson(msg, Map.class);
        excute();
    }
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        logger.info("建立连接。。。。");
        super.afterConnectionEstablished(session);
    }




    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        logger.info("连接关闭。。。。");
        super.afterConnectionClosed(session, status);
    }

}
