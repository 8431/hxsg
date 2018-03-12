package com.hxsg.Dao;

import com.hxsg.po.YlDaXiao;

import java.util.List;

public interface YlDaXiaoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(YlDaXiao record);
    List<YlDaXiao> getAll();
    YlDaXiao getDxAgo();
    List <YlDaXiao>getHistory();
    int insertSelective(YlDaXiao record);

    YlDaXiao selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(YlDaXiao record);

    int updateByPrimaryKey(YlDaXiao record);
}