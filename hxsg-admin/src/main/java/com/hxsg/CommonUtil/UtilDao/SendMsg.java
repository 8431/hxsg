package com.hxsg.CommonUtil.UtilDao;

/**
 * Created by dlf on 2016/3/15.
 */
public interface SendMsg {
    public  void sendMsgFriends(Integer Roleid, Integer Friendid, String rolename);
    public  void sendMsg(Integer roleid, String rolename, String type, String mseeage);
}
