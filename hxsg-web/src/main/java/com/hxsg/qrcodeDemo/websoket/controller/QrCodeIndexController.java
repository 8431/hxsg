package com.hxsg.qrcodeDemo.websoket.controller;

import com.hxsg.qrcodeDemo.websoket.QrCodeWebScoketController;
import com.hxsg.index.service.Cocos2dIndexService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Created by dlf on 2016/9/29.
 */
@Controller
@RequestMapping("qrcode")
public class QrCodeIndexController {
    /********************cocos2d-js服务端代码***************************/
    private Logger logger =Logger.getLogger(QrCodeIndexController.class);
    @Autowired
    Cocos2dIndexService cocos2dindexservice;
    //获取玩家详情
    @RequestMapping(value = "/demo", method = { RequestMethod.GET, RequestMethod.POST })
    @ResponseBody
    public String indexRoleMsg(HttpSession session,HttpServletRequest request,HttpServletResponse response){
        try{

            Map<String,WebSocketSession> mp= QrCodeWebScoketController.mp;
                for(String p:mp.keySet()){
                    WebSocketSession ws= null;
                    try {
                        ws = mp.get(p);
                        if(!session.equals(ws)){
                            ws.sendMessage(new TextMessage("xx"));
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }

        }catch (Exception e){
            }
        return null;
    }


}
