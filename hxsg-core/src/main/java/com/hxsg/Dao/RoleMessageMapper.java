package com.hxsg.Dao;

import com.hxsg.po.roleMessage;

import java.util.List;

public interface RoleMessageMapper {
    int deleteByPrimaryKey(Integer id);
    int insert(roleMessage record);
    int insertSelective(roleMessage record);
    List<roleMessage> getMsg();
    /**
     * 根据最新时间查询区的信息
     * @param type 1区3商
     * @return
     */
    List<roleMessage> getMsgType(String type);
    roleMessage selectByPrimaryKey(Integer id);
    int updateByPrimaryKeySelective(roleMessage record);
    int updateByPrimaryKey(roleMessage record);
}