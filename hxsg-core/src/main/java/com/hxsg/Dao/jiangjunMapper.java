package com.hxsg.Dao;

import com.hxsg.po.jiangjun;

import java.util.List;

public interface jiangjunMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(jiangjun record);

    int insertSelective(jiangjun record);

    jiangjun selectByPrimaryKey(Integer id);
    List<jiangjun> selectByRoleId(Integer id);
    List<jiangjun> selectAll(jiangjun record);
    int updateByPrimaryKeySelective(jiangjun record);

    int updateByPrimaryKey(jiangjun record);
}