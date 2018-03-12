package com.hxsg.Dao;

import com.hxsg.po.MoneyRecord;

import java.util.List;

public interface MoneyRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MoneyRecord record);

    int insertSelective(MoneyRecord record) throws  RuntimeException;;

    MoneyRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MoneyRecord record);

    int updateByPrimaryKey(MoneyRecord record);
    /**
     * 查询金银存取记录
     * @param md
     * @return
     */
    List<MoneyRecord> querymoneyRecord(MoneyRecord md);
}