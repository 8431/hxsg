package com.hxsg.Dao;

import com.hxsg.po.YouJian;

import java.util.List;

public interface YouJianMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(YouJian record);

    int insertSelective(YouJian record);
    List<YouJian> selectAllBytime();
    YouJian selectByPrimaryKey(Integer id);
   List<YouJian> selectStatus(Integer id);

    List<YouJian> selectAll(YouJian record);
    int updateByPrimaryKeySelective(YouJian record);

    int updateByPrimaryKey(YouJian record);
}