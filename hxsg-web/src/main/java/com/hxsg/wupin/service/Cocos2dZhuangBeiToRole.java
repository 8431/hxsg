package com.hxsg.wupin.service;

import org.springframework.stereotype.Service;

/**
 * Created by dlf on 2016/10/24.
 */

public interface Cocos2dZhuangBeiToRole {
    /**
     * 将装备给人物装备上去
     * @param id 道具Id
     * @return
     */
    public String zhuangBeiToRole(Integer roleid,Integer id,String type,Boolean re);
}
