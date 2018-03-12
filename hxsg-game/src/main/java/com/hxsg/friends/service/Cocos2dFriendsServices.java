package com.hxsg.friends.service;
import com.hxsg.po.NewRole;
import com.hxsg.po.RoleFriends;
import com.hxsg.po.RoleFriendsMsg;
import com.hxsg.vo.FriendsVo;
import com.hxsg.vo.PlayDetail;

import java.util.List;

/**
 * Created by dlf on 2016/12/12.
 */
public interface Cocos2dFriendsServices {
    /**发送信息给玩家
     * @param msg
     * @return
     */
    RoleFriendsMsg  sendMsgForFriends(RoleFriendsMsg msg)throws Exception;

    /**
     * 加好友
     * @param rf
     * @return
     * @throws Exception
     */
     String addFriends(RoleFriends rf)throws Exception;

    /**
     * 发送加好友请求
     * *******提示信息***********
     * 1.玩家【咕叽】请求加你为好友
     * 2.玩家【咕叽】拒绝你添加为好友
     * 上面的提示信息由客户端发送而来
     * @param rf
     * @return
     */
    void sendAddFriendsMsg(RoleFriendsMsg rf)throws Exception;

    /**
     * 禁言，拉黑，正常
     * @param rf
     * @return
     * @throws Exception
     */
    String speakStatus(RoleFriends rf)throws Exception;

    /**
     * 查询status(好友，亲人（师傅，徒弟，配偶，结拜），仇人，最近)需要判断该玩家是否在线
     * 在线：红色
     * 离线：蓝色
     * @param rf
     * @return
     * @throws Exception
     */
    List<FriendsVo> queryRoleFriends(RoleFriends rf)throws Exception;

    /**
     * 解除关系（断交好友）
     * @param rf
     * @return
     * @throws Exception
     */
    String relieveStatus(RoleFriends rf)throws Exception;

    /**
     * 加载好友聊天信息，一周之内的前面20条。
     * @param msg
     * @return
     * @throws Exception
     */
    List<RoleFriendsMsg> loadFrinedsMsg(RoleFriendsMsg msg) throws Exception;
    /**
     * 模糊查询。
     * @param msg
     * @return
     * @throws Exception
     */
    List<NewRole>  queryFriends(String msg,String type) throws Exception;





}
