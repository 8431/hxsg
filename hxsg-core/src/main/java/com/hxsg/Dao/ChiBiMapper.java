package com.hxsg.Dao;

import com.hxsg.po.ChiBi;

import java.util.List;

public interface ChiBiMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ChiBi record);

    int insertSelective(ChiBi record);

    ChiBi selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ChiBi record);

    int updateByPrimaryKey(ChiBi record);

    /**赤壁历史查询
     * 查询前面20条记录
     * @return
     */
    List<ChiBi> queryHistory();
}