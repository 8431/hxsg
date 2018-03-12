package com.hxsg.xunlian.service;

import com.hxsg.po.NewRole;
import com.hxsg.po.XunLian;

import java.util.List;

/**
 * Created by dlf on 2017/1/7.
 * 功能-训练接口
 */
public interface Cocos2dXunLianService {
    /**
     * 查询训练玩家
     * @return
     */
    public List<XunLian> Training(String status)throws Exception;

    public String StartTraining(Integer roleId,Integer time) throws Exception;

    /**
     * 查询某个玩家的训练详情
     * @param roleId
     * @return
     * @throws Exception
     */
    public XunLian queryJingYan(Integer roleId) throws Exception;

    /**
     * 领取经验
     * @param roleId
     * @return
     * @throws Exception
     */

    public String getJingYan(Integer roleId)throws Exception;

    /**
     * 将经验转化为角色等级，----升级
     * @param roleId
     * @param jingYan
     * @throws Exception
     */
    public void expConversionLevel(Integer roleId,Integer jingYan)throws Exception;

}
