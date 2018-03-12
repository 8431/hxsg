package com.hxsg.Dao;

import com.hxsg.po.WuQiDetail;

import java.util.List;

public interface WuQiDetailMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WuQiDetail record);

    WuQiDetail selectByZbId(Integer id);
    int insertSelective(WuQiDetail record);
    List<WuQiDetail> selectAll(WuQiDetail record);
    WuQiDetail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WuQiDetail record);

    int updateByPrimaryKey(WuQiDetail record);
}