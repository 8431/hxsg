package com.hxsg.qianzhuang.service;

import com.hxsg.po.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * Created by dlf on 2016/11/29.
 */
public interface MoneyService {
    /**
     * 查询角色随身携带金银
     * @return
     */
    NewRole queryRoleMoney(HttpSession session);

    /**
     * 设置银两操作

     */
    String setMoney(HttpSession session, NewRole re, String status) throws Exception;

    /**
     * 钱庄银两操作
     * @param session
     * @param re
     * @param status
     * @return
     */
    String setQianZhuangMoney(HttpSession session, RoleMoney re, String status) throws Exception;

    /**
     * 钱庄存取钱
     * @param session
     * @param ne
     * @param re
     * @param status
     * @return
     */
    String money(HttpSession session, NewRole ne, RoleMoney re, String status);

    /**
     * 查询供求金砖的前6条信息
     * @return
     */
    List<GoldBusiness> queryGoldBusiness()throws Exception;
    /**
     * 根据价格和状态（供，求）查询出价人和详细出价数量
     * @param gb
     * @return
     */
    List<GoldBusiness>queryGoldShopMsg(GoldBusiness gb)throws Exception;

    /**
     * 求，供金砖
     * @param gb
     * @return
     */
    String sellOrBuy(GoldBusiness gb);

    /**
     * 查询某个玩家截止到当前时间的前N天，出售或求购中的 已成交的交易详情
     * select * from gold_record where date between current_date()-7  and  sysdate() and buyRoleid=1000 and type='求'
     * @param gb
     * @return
     */
    List<GoldRecord> queryTransactionRecordForGold(GoldRecord gb);
    /**
     * 查询某个玩家截止到当前时间的前N天，出售或求购中的 未成交的交易详情
     SELECT * FROM gold_business g where type='求'  and status='出售中' and date between current_date()-7  and  sysdate() and roleid=1000
     * @param gb
     * @return
     */
    List<GoldBusiness> queryNoTransactionRecordForGold(GoldBusiness gb);

    /**
     * 撤销订单
     * @param gb
     * @return
     */
    String cancelOrder(GoldBusiness gb)throws Exception;

    /**
     * 加载银两，金砖取款界面
     * @param roleId
     * @return
     */
    Map<String,Object> queryQuKuanLoading(Integer roleId)throws Exception;

    /**
     * 查询金银存取记录
     * @param md
     * @return
     */
    List<MoneyRecord>querymoneyRecord(MoneyRecord md)throws Exception;
}
