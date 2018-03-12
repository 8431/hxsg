package com.hxsg.Dao;

import com.hxsg.po.YeGuaiQun;

public interface YeGuaiQunMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(YeGuaiQun record);

    int insertSelective(YeGuaiQun record);

    YeGuaiQun selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(YeGuaiQun record);

    int updateByPrimaryKey(YeGuaiQun record);

    /**
     * 查询野怪群详细分布
     * @param name
     * @return
     */
    YeGuaiQun selectByName(String name);
}