package com.hxsg.wupin.service;

import com.hxsg.po.*;
import com.hxsg.vo.IndexFuJiangVo;
import com.hxsg.vo.ZaWuDetailVo;
import com.hxsg.vo.ZhuangBeiDetailVo;

import java.util.List;

/**
 * Created by dlf on 2015/12/31.
 */
public interface Cocos2dUserWuPinService {
    /**
     * 使用道具
     * @param wupinId
     * @param roleId
     * @return
     */
    String userDaoJu(Integer wupinId,Integer roleId,Integer num,Integer jnId,Integer fuId) throws Exception;
    /**
     * 使用将军令培养副将属性
     * @param
     * @param roleId
     * @return
     */
    Object peiYangFuJiang(Integer fuId, Integer roleId,String type)throws  Exception;
    /**
    *获取可以使用该技能书的人物列表和技能列表
    * @param roleid 角色ID
    * @param level  技能书等级
    * @return
    * @throws Exception
    */
    List<Skill> querySkillForRole(Integer roleid,Integer level,Integer fuId) throws  Exception;
    /**
     * 获取加载使用心法书的副将界面
     * @param wupinId
     * @return
     * @throws Exception
     */
    public   List<RoleFujiang> getRoleFuforUserXinFaShu(Integer roleid,Integer wupinId)throws  Exception;

    /**
     * 学习技能时，查询可以学习改技能的副将
     * @param wuPinId
     * @param roleId
     * @return
     */
    List<IndexFuJiangVo>queryFuJiangJiNengBook(Integer wuPinId,Integer roleId)throws  Exception;
}


