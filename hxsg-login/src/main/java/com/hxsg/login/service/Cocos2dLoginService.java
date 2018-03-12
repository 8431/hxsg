package com.hxsg.login.service;


import com.hxsg.CommonUtil.CommonException;
import com.hxsg.po.Acount;
import com.hxsg.po.NewRole;
import java.util.List;

/**
 * Created by dlf on 2015/12/31.
 */
public interface Cocos2dLoginService {
    /**
     * cocos2d-js检查role中是否存在相同数据，验证注册时角色名是否相同，账号是否相同
     * @param re
     * @return true, fasle
     */
    public String checkRole(NewRole re) throws CommonException;
    /**
     * cocos2d-js检查账号是否重复
     * @param re
     * @return true, fasle
     */
    public String checkAcount(Acount re) throws CommonException;
    /**
     * cocos2d-js创建角色
     *
     * @param re
     * @return true, fasle
     * @throws Exception
     */
    public String creatRole(NewRole re) throws CommonException;
    /**
     * cocos2d-js创建账号
     *
     * @param re
     * @return true, fasle
     * @throws Exception
     */
    public String creatAccount(Acount re) throws CommonException;
    /**
     * cocos2d-js登录
     *
     * @param re
     * @return
     * @throws Exception
     */
    public Object login(Acount re) throws CommonException;

    /**
     * 选择角色界面
     * @param re
     * @return
     * @throws Exception
     */
    public Object selectRole(NewRole re) throws CommonException;

    /**
     * 加载选择角色界面
     * @return
     * @throws Exception
     */
    public List<NewRole> LoadSelectRole(String key) throws CommonException;

    /**
     * 校验游戏是否需要更新
     * @return
     * @throws CommonException
     */
    public  String checkVersion() throws CommonException;

}
