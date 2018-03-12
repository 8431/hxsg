package com.hxsg.Dao;

import com.hxsg.po.YouJianWuPin;

import java.util.List;

public interface YouJianWuPinMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(YouJianWuPin record);

    int insertSelective(YouJianWuPin record);
    List<YouJianWuPin> selectAll(YouJianWuPin record);


    YouJianWuPin selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(YouJianWuPin record);

    int updateByPrimaryKey(YouJianWuPin record);
}