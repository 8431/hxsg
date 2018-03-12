package com.hxsg.Dao;

import com.hxsg.po.GoldBusiness;

import java.util.List;

public interface GoldBusinessMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GoldBusiness record);

    int insertSelective(GoldBusiness record);

    GoldBusiness selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GoldBusiness record);

    int updateByPrimaryKey(GoldBusiness record);
    /**
     * 查询供求金砖的前6条信息
     * @return
     */
    List<GoldBusiness> queryGoldBusiness() throws Exception;

    /**
     * 根据价格和状态（供，求）查询出价人和详细出价数量
     * @param gb
     * @return
     */
    List<GoldBusiness>queryGoldShopMsg(GoldBusiness gb)throws Exception;

    /**
     * 出售的最低价格
     * @return
     */
    Integer minSell()throws Exception;

    /**
     * 求购的最高价格
     * @return
     */
    Integer maxBuy()throws Exception;
    /**
     * 查询某个玩家截止到当前时间的前N天，出售或求购中的 未成交的交易详情
     SELECT * FROM gold_business g where type='求'  and status='出售中' and date between current_date()-7  and  sysdate() and roleid=1000
     * @param gb
     * @return
     */
    List<GoldBusiness> queryNoTransactionRecordForGold (GoldBusiness gb)throws Exception;
}