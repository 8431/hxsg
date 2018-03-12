package com.hxsg.Dao;

import com.hxsg.po.roleShuxing;

public interface roleShuxingMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(roleShuxing record);

    int insertSelective(roleShuxing record);

    roleShuxing selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(roleShuxing record);

    int updateByPrimaryKey(roleShuxing record);
}