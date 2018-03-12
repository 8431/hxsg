package com.hxsg.Dao;

import com.hxsg.po.Tax;

public interface TaxMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Tax record);

    int insertSelective(Tax record);

    Tax selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Tax record);

    int updateByPrimaryKey(Tax record);
}