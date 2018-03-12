package com.hxsg.login.websoket;


import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.hxsg.CommonUtil.login.Constants;
import com.hxsg.CommonUtil.util.MapUtil;
import com.hxsg.login.LoginService;
import com.hxsg.login.zhuceService;
import com.hxsg.po.Role;
import com.hxsg.vo.SystemMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.*;
import java.lang.Exception;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.util.*;


/**
 * 用户通讯聊天控制器类
 *
 * @author yyp
 *         2014年12月27日下午6:56:46
 */

@RequestMapping("login")
public class loginWebScoketController extends TextWebSocketHandler implements HandshakeInterceptor {

    private Logger logger = LoggerFactory.getLogger(getClass());
    private HttpSession httpsession;
    @Autowired
    LoginService loginservice;
    @Autowired
    zhuceService zhuceservice;
    Map<String,Object> mp=Constants.SESSION_NAME;
    @Override
    protected void handleTextMessage(WebSocketSession session,TextMessage message){
        try {
            String msg = message.getPayload();
            Gson gn = new Gson();
            Map<String,String> dataMap=gn.fromJson(msg,Map.class);
            String type=dataMap.get("type");
            Role re = gn.fromJson(dataMap.get("data"),Role.class);
            if(type.equals("login")){

                List<Role> li = loginservice.YzLogin(re);
                if(li!=null&&li.size()>0){
                    String roleid= (String) httpsession.getAttribute("roleid");
                    String id = li.get(0).getId().toString();
                    Map<String,Object> mpSession=Constants.HTTPSESSION;
                    //String  mpid=id+session.getId();
                    String key=MapUtil.getRoleId(mp, session);
                    System.out.println(key);
                        if (id.equals(key)) {
                            SystemMsg sg=new SystemMsg();
                            sg.setMsg("用户在别处登录，您已被强制下线！");
                            TextMessage te = new TextMessage( gn.toJson(sg));
                            WebSocketSession wn = (WebSocketSession) mp.get(key);
                            wn.sendMessage(te);
                            mp.remove(key);
                            mp.put(id,session);
                        }else{
                            TextMessage tm = new TextMessage(gn.toJson(li.get(0)));
                            session.sendMessage(tm);
                            mp.put(id,session);
                        }
                }else{
                    session.sendMessage(new TextMessage("false"));
                }
            }
            if(type.equals("createRole")){
                    Boolean result=zhuceservice.appCreatrole(re,session);
                    TextMessage te = new TextMessage(result.toString());
                    session.sendMessage(te);
            }
            if(type.equals("register")){
                Boolean result=zhuceservice.appCreatrole(re,session);
                TextMessage te = new TextMessage(result.toString());
                session.sendMessage(te);
            }
        } catch (Exception e) {
            logger.error("webscoket登录验证异常loginWebScoketController" + e.getMessage());
            try {
                session.sendMessage(new TextMessage("false"));
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {


        System.out.println("Connection open！");
    }
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        System.out.println("Connection Closed！");
        MapUtil.getRoleId(mp, session);
    }
    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        super.handleTransportError(session, exception);
    }

    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler webSocketHandler, Map<String, Object> stringObjectMap) throws Exception {
        if (request instanceof ServletServerHttpRequest) {
            ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
            HttpSession session = servletRequest.getServletRequest().getSession(true);

            if (session != null) {
                this.httpsession=session;
            }else{
                logger.debug("httpsession is null");
            }
        }
        return true;
    }

    @Override
    public void afterHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Exception e) {

    }
}
