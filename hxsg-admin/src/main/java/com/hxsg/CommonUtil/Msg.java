package com.hxsg.CommonUtil;

import com.hxsg.po.RoleFriendsMsg;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * Created by dlf on 2016/1/27.
 */

public class Msg {
    @Autowired
    ServiceDao ServiceDao;
    public  void sendMsg(Integer Roleid,Integer Friendid,String rolename){
        RoleFriendsMsg rg = new RoleFriendsMsg();
        rg.setRoleid(Roleid);
        rg.setFriendid(Friendid);
        rg.setMessage("【" + rolename + "】提取了你发送的物品！");
        rg.setData(new Date());
        rg.setType("通知");
        int a = ServiceDao.getRoleFriendsMsg(rg);
    }

}
