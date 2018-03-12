package com.hxsg.Dao;

import com.hxsg.po.FuJiang;

import java.util.List;

public interface FuJiangMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(FuJiang record);

    int insertSelective(FuJiang record);
    List<FuJiang> selectAll(FuJiang record);
    FuJiang selectByPrimaryKey(Integer id);
    List<FuJiang> queryFuJiangByName(String ... s);
    int updateByPrimaryKeySelective(FuJiang record);

    int updateByPrimaryKey(FuJiang record);
}