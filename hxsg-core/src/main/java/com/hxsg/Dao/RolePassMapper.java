package com.hxsg.Dao;

import com.hxsg.po.rolePass;

import java.util.List;

public interface RolePassMapper {
    int deleteByPrimaryKey(Integer id);
    List<rolePass> login(rolePass record);
    int insert(rolePass record);
    List<rolePass> getRole(rolePass record);
    int insertSelective(rolePass record);

    rolePass selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(rolePass record);

    int updateByPrimaryKey(rolePass record);
}