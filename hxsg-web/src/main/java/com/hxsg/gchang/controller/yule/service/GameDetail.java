package com.hxsg.gchang.controller.yule.service;

/**
 * Created by dlf on 2017/3/8.
 */
public interface GameDetail {
    /**
     * 游戏算法实现
     * 由具体实现类实现各游戏的玩法
     * @return
     * @throws Exception
     */
    Object GameAI()throws Exception;
    /**
     * 查询游戏结果历史记录
     * @return
     */
     Object queryHistoryRecord() throws Exception;

    /**
     * 查询玩家游戏赢钱记录总排行榜
     * @return
     */
    Object queryWinRecord()throws Exception;

    /**查询自己下注记录
     *
     * @return
     */
    Object queryMySelfRecord(Integer roleid)throws Exception;

    /**
     * 查询游戏当前期数赢家排行榜
     * @return
     * @throws Exception
     */
    Object queryWinRecordforNum(Integer num)throws Exception;

}
