package com.hxsg.Dao;

import com.hxsg.po.MapGuai;

public interface MapGuaiMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MapGuai record);

    int insertSelective(MapGuai record);

    MapGuai selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MapGuai record);

    int updateByPrimaryKey(MapGuai record);

    /**
     * 查询地图野怪
     * @param city
     * @return
     */
    MapGuai selectByCity(String city);
}