package com.hxsg.index.websoket;
import com.google.gson.Gson;
import com.hxsg.CommonUtil.login.Constants;
import com.hxsg.CommonUtil.login.Login;
import com.hxsg.CommonUtil.util.MapUtil;
import com.hxsg.Dao.roleMessageMapper;
import com.hxsg.login.LoginService;
import com.hxsg.po.roleMessage;
import com.hxsg.system.dao.SystemNotification;
import org.apache.log4j.Logger;
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

import java.util.*;


/**
 * 用户通讯聊天控制器类
 *
 * @author yyp
 *         2014年12月27日下午6:56:46
 */

@RequestMapping("chat")
public class indexWebScoketController extends TextWebSocketHandler implements HandshakeInterceptor {
    private Logger logger = Logger.getLogger(indexWebScoketController.class);
    Map<String, Object> mp = Constants.SESSION_NAME;
    @Autowired
    LoginService loginservice;
    @Autowired
    roleMessageMapper rolemessagemapper;
    @Autowired
    SystemNotification systemnotification;
    /**   1.验证登录
     *    2.加入MAP
     *    3.将信息加入数据库
     *    4.推送到主界面聊天
     */
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) {
        try {
            String msg = message.getPayload();
            Gson gn = new Gson();
            Map<String, String> dataMap = gn.fromJson(msg, Map.class);
            String type = dataMap.get("type");
            String typeChat = dataMap.get("typeChat");
            String key = dataMap.get("key");
           // Role re = gn.fromJson(dataMap.get("role"), Role.class);
            String chatMsg = dataMap.get("chatMsg");
            Login login= (Login) Constants.loginMap.get(key);
            if(login!=null){
                Integer roleId=   login.getRoleId();
                String roleName=  login.getRoleName();
                Integer id =roleId;
                if (type.equals("login")) {
                    System.out.println("------------------------login");
                    mp.remove(id.toString());
                    mp.put(id.toString(), session);
                }
                if (type.equals("chat")) {
                    switch (typeChat){
                        case "2":{
                            //教派聊天
                        }
                        case "4":{

                        }
                        /**
                         * 需要整改 ---------性能低下---------
                         */
                        default:{
                            if(!StringUtils.isEmpty(chatMsg)){
                                if(chatMsg.length()>50){
                                    chatMsg= chatMsg.substring(0,50);
                                }
                            insertRoleMessqge(typeChat, chatMsg,roleName, id);
                            //读取数据库最新20条数据
                            List<roleMessage> li=rolemessagemapper.getMsgType(typeChat);
                            systemnotification.sendSystemMsg(new Object[]{typeChat,li});
                        }
                    }
                }
            }
            }
        } catch (Exception e) {
            logger.error("webscoket登录验证异常loginWebScoketController" + e.getMessage(),e);
        }
    }

    public Boolean insertRoleMessqge(String typeChat, String chatMsg, String roleName, Integer id) throws Exception{
        boolean r=false;
        roleMessage rme=new roleMessage();
        rme.setRoleid(id);
        rme.setData(new Date());
        rme.setRolename(roleName);
        rme.setMessage(chatMsg);
        rme.setType(typeChat);//1区-1派-3商-3世
        //插入最新消息
        int result =rolemessagemapper.insertSelective(rme);
        if(result>0){
            r=true;
        }
        return r;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {

        System.out.println("Connection open！");
    }
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {

        try {
            System.out.println("Connection Closed！");
            String key=MapUtil.getRoleId(mp, session);
            mp.remove(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        super.handleTransportError(session, exception);
    }

    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler webSocketHandler, Map<String, Object> stringObjectMap) throws Exception {
        if (request instanceof ServletServerHttpRequest) {
//            ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
//            HttpSession httpsession = servletRequest.getServletRequest().getSession(true);
//            Map<String,HttpSession> mp=Cocos2dHttpSessionListener.HTTPSESSIONMAP;
//            Integer roleid= (Integer) httpsession.getAttribute("roleId");
//            mp.put(roleid.toString(),httpsession);
            //带完善同时登陆的问题
        }
        return true;
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler webSocketHandler, Exception e) {

    }

}
