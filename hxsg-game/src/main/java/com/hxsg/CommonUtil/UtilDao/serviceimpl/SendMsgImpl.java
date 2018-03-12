package com.hxsg.CommonUtil.UtilDao.serviceimpl;

import com.hxsg.CommonUtil.UtilDao.SendMsg;
import com.hxsg.Dao.RoleFriendsMsgMapper;
import com.hxsg.Dao.RoleMapper;
import com.hxsg.Dao.RoleMoneyMapper;
import com.hxsg.Dao.roleMessageMapper;
import com.hxsg.po.RoleFriendsMsg;
import com.hxsg.po.roleMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * Created by dlf on 2016/3/15.
 */
@Service("SendMsg")
public class SendMsgImpl{
    @Autowired
    roleMessageMapper rmm;
    @Autowired
    RoleFriendsMsgMapper rfmm;
    @Autowired
    RoleMapper rm;


    public void sendMsgAll(Integer Roleid, Integer Friendid, String msg,String type) {
        RoleFriendsMsg rg = new RoleFriendsMsg();
        rg.setRoleid(Roleid);
        rg.setFriendid(Friendid);
        rg.setMessage(msg);
        rg.setData(new Date());
        rg.setType(type);
        rfmm.insertSelective(rg);


    }


    public void sendMsg( Integer roleid,String rolename,String type,String mseeage)  {


        roleMessage  rme=new roleMessage();
        rme.setData(new Date());
        rme.setRoleid(roleid);
        rme.setRolename(rolename);
        rme.setType(type);
        rme.setMessage(mseeage);
        rmm.insertSelective(rme);
    }
}
