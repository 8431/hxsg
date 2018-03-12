package com.hxsg.Dao;

import com.hxsg.po.RoleZbDetail;

import java.util.List;

public interface RoleZbDetailMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RoleZbDetail record);

    int insertSelective(RoleZbDetail record);

    RoleZbDetail selectByPrimaryKey(Integer id);
    List<RoleZbDetail> selectAll(RoleZbDetail record);
    int updateByPrimaryKeySelective(RoleZbDetail record);

    int updateByPrimaryKey(RoleZbDetail record);
}