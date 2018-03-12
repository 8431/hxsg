package com.hxsg.Dao;

import com.hxsg.po.WaBao;

import java.util.List;

public interface WaBaoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WaBao record);

    int insertSelective(WaBao record);
    List<WaBao> selectAll(WaBao record);
    WaBao selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WaBao record);

    int updateByPrimaryKey(WaBao record);
}