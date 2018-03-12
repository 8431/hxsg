package com.hxsg.dao;

import com.hxsg.CommonUtil.CommonException;

/**
 * Created by dlf on 2018/1/15 0015.
 * Email 1429264916@qq.com
 */
public interface HandlePkService {
    /**
     * 处理pk逻辑流程
     * @param roleid
     * @param uuid
     * @throws CommonException
     */
     void handlePk(String roleid,String uuid) throws CommonException;
}
