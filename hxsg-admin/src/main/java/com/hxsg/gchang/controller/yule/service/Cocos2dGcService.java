package com.hxsg.gchang.controller.yule.service;


import com.hxsg.po.YlDxXq;
import com.hxsg.vo.CasinoMsg;

/**
 * Created by dlf on 2015/12/31.
 */
public interface Cocos2dGcService {
    /**
     * 查询赌场玩家赢得金银信息
     * @return
     */
  public Object queryWinMoneyRole()throws Exception;
    /**
     * 查询赌场-娱乐场-赚钱排行
     * @return
     */
    public Object queryWinJinBang()throws Exception;

    /**
     * 查询赌场-娱乐场-历史查询
     * @return
     */
    public Object queryCasinoHistory()throws Exception;
    /**
     * 查询赌场-娱乐场-投注记录
     * @param roleid 角色id
     * @return
     */
    public Object queryBettingRecord(Integer roleid) throws Exception;

    /**
     * 生成赌场-开盘数据
     */
    public void DataGeneration() throws Exception;

    /**
     * 刷新赌场页面押注数据
     * @return
     */
    public CasinoMsg queryCasinoMsg() throws Exception;

    /**
     * 角色押注结果信息
     * @param roleId 角色id
     * @param yq 押注信息
     * @return
     */
    public String roleStakeResult(Integer roleId, YlDxXq yq)throws Exception;

}
