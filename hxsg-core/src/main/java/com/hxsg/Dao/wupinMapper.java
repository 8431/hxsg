package com.hxsg.Dao;

import com.hxsg.po.wupin;

public interface wupinMapper {
    int deleteByPrimaryKey(Integer roleid);

    int insert(wupin record);

    int insertSelective(wupin record);

    wupin selectByPrimaryKey(Integer roleid);

    int updateByPrimaryKeySelective(wupin record);

    int updateByPrimaryKey(wupin record);
}