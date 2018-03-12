package com.hxsg.Dao;

import com.hxsg.po.RoleFriends;

import java.util.List;

public interface RoleFriendsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RoleFriends record);
    List<RoleFriends> selectAll(RoleFriends record);
    int insertSelective(RoleFriends record);

    RoleFriends selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RoleFriends record);

    int updateByPrimaryKey(RoleFriends record);

    /**
     * 查询亲人----师父，徒弟,配偶，结拜
     * @param id
     * @return
     */
    List<RoleFriends> queryQinRen(Integer id);


}