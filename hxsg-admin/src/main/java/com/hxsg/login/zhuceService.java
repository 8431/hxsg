package com.hxsg.login;


import com.hxsg.po.Role;
import com.hxsg.po.rolePass;
import org.springframework.web.socket.WebSocketSession;

import javax.servlet.http.HttpSession;

/**
 * Created by dlf on 2015/12/31.
 */
public interface zhuceService {

    public boolean getZhuCe(Role re);

    /**
     * 检查role中是否存在相同数据，验证注册时角色名是否相同，账号是否相同
     * @param re
     * @return true,fasle
     */
    public boolean checkRole(Role re) throws Exception;

    /**
     * cocos2d-js创建角色
     * @param re
     * @return true,fasle
     * @throws Exception
     */
    public Boolean creatRole(Role re)throws Exception;

    /**
     * 创建角色
     * @param re
     * @param session
     * @return
     * @throws Exception
     */
    public Boolean appCreatrole(Role re, WebSocketSession session)throws Exception;
}
