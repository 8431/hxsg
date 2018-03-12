package com.hxsg.Dao;

import com.hxsg.po.WuqiDescribe;

public interface WuqiDescribeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WuqiDescribe record);

    int insertSelective(WuqiDescribe record);

    WuqiDescribe selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WuqiDescribe record);

    int updateByPrimaryKey(WuqiDescribe record);
}