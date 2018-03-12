package com.hxsg.Dao;

import com.hxsg.po.RoleFriendsMsg;

import java.util.List;

public interface RoleFriendsMsgMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RoleFriendsMsg record);

    int insertSelective(RoleFriendsMsg record);

    RoleFriendsMsg selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RoleFriendsMsg record);

    int updateByPrimaryKey(RoleFriendsMsg record);
    List<RoleFriendsMsg> selectAll(RoleFriendsMsg record);
    List<RoleFriendsMsg> getTalkMsgByTime(Integer rid1,Integer fid1,Integer rid2,Integer fid2);

    /**
     * 查询最近时间中的前20个玩家
     * @param roleId
     * @return
     */
    List<RoleFriendsMsg> queryFriendsByTime(Integer roleId) throws Exception;

    /**
     * 查询一周内的聊天记录
     * @param roleId
     * @param friendId
     * @return
     * @throws Exception
     */
    List<RoleFriendsMsg> queryChatRecord(Integer roleId,Integer friendId)throws Exception;
}