package com.hxsg.index.service;


import com.hxsg.po.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by dlf on 2015/12/31.
 */
public interface cocos2dIndexService {
    /**通过id查询角色信息详情
     * @param id role表主键值
     * @return Role
     */
   public NewRole queryRoleMsg(Integer id) throws Exception;

    /**
     * 根据主键查询角色表
     * @param id
     * @return
     * @throws Exception
     */
    public NewRole queryRoleById(Integer id) throws Exception;

    /**
     * 联合查询气血
     * @param id
     * @return
     * @throws Exception
     */
    public NewRole queryRoleMsgQx(Integer id) throws Exception;
    /**通过id查询角色信息详情
     * @param id role表主键值
     * @return Role
     */
   public NewRole queryRoleMsg(Integer id,Integer friendId) throws Exception;
    /**主界面初始化加载聊天信息
     */
    public Object[] chatMsg() throws Exception;

    /**
     * 通过中心城市定位方位
     * @return
     * @throws RuntimeException
     */
    public GjsgMap moveCity(String city,HttpSession session) throws RuntimeException, Exception;

    /**
     * 查询该城市野怪
     * @param city
     * @return
     * @throws RuntimeException
     */
    public MapGuai queryMapGuai(String city) throws RuntimeException;

    /**
     * 查询野怪群详细野怪
     * @param name
     * @return
     * @throws RuntimeException
     */
    public YeGuaiQun queryYeGuaiQun(String name) throws RuntimeException;

    /**
     * 查询附近玩家
     * @param city
     * @return
     * @throws RuntimeException
     */
    public List<NewRole> nearbyRole (String city,String key)  throws Exception ;

}
