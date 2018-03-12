package com.hxsg.pk.websoket;

import com.hxsg.po.RoleFujiang;

/**
 * Created by Administrator on 2017/7/20 0020.
 */
public interface PkServerInitData {
    /**
     * 根据副将名称和等级初始化副将
     * @param name
     * @param level1
     * @param level2
     * @return
     * @throws Exception
     */
    RoleFujiang CreateRoleFujiang(String name,Integer level1,Integer level2,Integer roleId) throws Exception;
}
