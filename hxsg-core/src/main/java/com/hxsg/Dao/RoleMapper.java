package com.hxsg.Dao;

import com.hxsg.po.Role;

import java.util.List;

public interface RoleMapper {
    int deleteByPrimaryKey(Integer id);
    List<Role > login(Role record);
    int insert(Role record);
    List<Role > selectAll(Role record);
    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);
}