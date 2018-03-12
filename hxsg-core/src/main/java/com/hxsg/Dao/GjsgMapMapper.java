package com.hxsg.Dao;

import com.hxsg.po.GjsgMap;

public interface GjsgMapMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GjsgMap record);

    int insertSelective(GjsgMap record);

    GjsgMap selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GjsgMap record);

    int updateByPrimaryKey(GjsgMap record);

    /**
     * 通过中心城市获取方位
     * @param city
     * @return
     */
    GjsgMap selectByCenterCity(String city);
}