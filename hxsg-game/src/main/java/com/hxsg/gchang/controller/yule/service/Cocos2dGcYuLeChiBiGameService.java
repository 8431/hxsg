package com.hxsg.gchang.controller.yule.service;


import com.hxsg.po.ChibiYazhuDetail;
import com.hxsg.vo.ChiBiMsgVo;

/**
 * Created by dlf on 2015/12/31.
 */
public interface Cocos2dGcYuLeChiBiGameService extends GameDetail {

    /**
     * 角色押注结果信息
     * @param roleId 角色id
     * @param yq 押注信息
     * @return
     */
    public  String roleStakeResult(Integer roleId,ChibiYazhuDetail yq)throws Exception;

    /**
     * 加载赤壁页面
     * @param num
     * @return
     * @throws Exception
     */
    public ChiBiMsgVo loadChiBiDetail(Integer num)throws Exception;

    /**
     * 加载赤壁押注页面信息
     * @param roleId
     * @param num
     * @return
     * @throws Exception
     */
    public ChiBiMsgVo loadChiBiYaZhuMsg(Integer roleId,Integer num)throws Exception;



}
