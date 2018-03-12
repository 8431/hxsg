package com.hxsg;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

import javax.servlet.http.HttpSession;
import java.util.Map;


public class HandshakeInterceptor extends HttpSessionHandshakeInterceptor {
    private static Log logger = LogFactory.getLog(org.springframework.web.socket.server.HandshakeInterceptor.class);
    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object
            > attributes) throws Exception {
        logger.debug("beforeHandshake start.....");
        logger.debug(request.getClass().getName());
        if (request instanceof ServletServerHttpRequest) {
            ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
            HttpSession session = servletRequest.getServletRequest().getSession(false);
            if (session != null) {
                System.out.println("session"+  session.getId());
                //使用userName区分WebSocketHandler，以便定向发送消息
//                String userName = (String) session.getAttribute(Constants.SESSION_USERNAME);
//                logger.info(userName+" login");
//                attributes.put(Constants.WEBSOCKET_USERNAME,userName);
            }else{
                logger.debug("httpsession is null");
            }
        }
        return true;
    }
    @Override
    public void afterHandshake(ServerHttpRequest request,
                               ServerHttpResponse response, WebSocketHandler wsHandler,
                               Exception ex) {

        super.afterHandshake(request, response, wsHandler, ex);
    }
}

