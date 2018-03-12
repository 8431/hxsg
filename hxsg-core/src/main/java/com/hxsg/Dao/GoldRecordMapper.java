package com.hxsg.Dao;

import com.hxsg.po.GoldBusiness;
import com.hxsg.po.GoldRecord;

import java.util.List;

public interface GoldRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GoldRecord record);

    int insertSelective(GoldRecord record);

    GoldRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GoldRecord record);

    int updateByPrimaryKey(GoldRecord record);
    /**
     * 查询某个玩家截止到当前时间的前N天，出售或求购中的 已成交的交易详情
     * select * from gold_record where date between current_date()-7  and  sysdate() and buyRoleid=1000 and type='求'
     * @param gb
     * @return
     */
    List<GoldRecord> queryTransactionRecordForGold(GoldRecord gb);
}