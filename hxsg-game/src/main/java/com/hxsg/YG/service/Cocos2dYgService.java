package com.hxsg.YG.service;

import com.hxsg.po.roleFujiang;
import com.hxsg.vo.IndexFuJiangVo;

import java.util.List;

/**
 * Created by dlf on 2016/10/31.
 * 驿馆
 */
public interface Cocos2dYgService {
    /**
     * 查询人物可以佩戴的副将----主界面点击副将框数据
     * @param roleId 角色ID
     * @param shuxing 1，不在驿馆，0在驿馆
     * @return
     * @throws Exception
     */
    public List<IndexFuJiangVo> queryRoleFuJiang(Integer roleId,Integer shuxing) throws Exception;

    /**
     * 招领-留守副将
     * @param roleId
     * @param fuId
     * @return
     * @throws Exception
     */
    public String fuJiangZhaoLing(Integer roleId,Integer fuId)throws Exception;

}
