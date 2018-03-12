package com.hxsg.wupin.service;

/**
 * Created by dlf on 2017/1/7.
 */
public interface Cocos2dGiftPackageService {
    /**
     * 领取内测礼包
     * @param roleId
     * @return
     * @throws Exception
     */
    public String giftPackage(Integer roleId, String name)throws Exception;
}
