package com.hxsg.wupin.service;

import com.hxsg.CommonUtil.CommonException;

/**
 * Created by dlf on 2016/10/24.
 */

public interface Cocos2dZhuangBeiToRole {
    /**
     * 将装备给人物装备上去
     * @param id 道具Id
     * @return
     */
    public String zhuangBeiToRole(Integer roleid, Integer id, String type, Boolean re) throws CommonException;

    /**
     * 卸载装备
     * @param roleid
     * @param id
     * @return
     * @throws CommonException
     */

    public String xieZai(String roleid, String id) throws CommonException;
}
