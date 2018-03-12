package com.hxsg.login.service;


import com.hxsg.po.Acount;
import com.hxsg.po.NewRole;
import com.hxsg.po.Role;
import org.springframework.web.socket.WebSocketSession;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by dlf on 2015/12/31.
 */
public interface cocos2dLoginService {
    /**
     * cocos2d-js检查role中是否存在相同数据，验证注册时角色名是否相同，账号是否相同
     * @param re
     * @return true, fasle
     */
    public String checkRole(NewRole re) throws Exception;
    /**
     * cocos2d-js检查账号是否重复
     * @param re
     * @return true, fasle
     */
    public String checkAcount(Acount re) throws Exception;
    /**
     * cocos2d-js创建角色
     *
     * @param re
     * @return true, fasle
     * @throws Exception
     */
    public String creatRole(NewRole re, HttpServletRequest request) throws Exception;
    /**
     * cocos2d-js创建账号
     *
     * @param re
     * @return true, fasle
     * @throws Exception
     */
    public String creatAccount(Acount re) throws Exception;
    /**
     * cocos2d-js登录
     *
     * @param re
     * @return
     * @throws Exception
     */
    public Object login(Acount re, HttpSession session) throws Exception;

    /**
     * 选择角色界面
     * @param re
     * @param session
     * @return
     * @throws Exception
     */
    public Object selectRole(NewRole re, HttpServletRequest request) throws Exception;

    /**
     * 加载选择角色界面
     * @param session
     * @return
     * @throws Exception
     */
    public List<NewRole> LoadSelectRole(HttpSession session) throws Exception;

}
