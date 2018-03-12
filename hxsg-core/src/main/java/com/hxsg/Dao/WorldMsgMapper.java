package com.hxsg.Dao;

import com.hxsg.po.WorldMsg;

import java.util.List;

public interface WorldMsgMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WorldMsg record);

    int insertSelective(WorldMsg record);

    WorldMsg selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WorldMsg record);

    int updateByPrimaryKey(WorldMsg record);

    /**
     * 根据最新时间的前20条数据m
     * @return
     */
    List<WorldMsg> queryWorldMsg();
}