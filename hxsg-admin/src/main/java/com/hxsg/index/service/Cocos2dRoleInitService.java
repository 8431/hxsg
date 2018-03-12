package com.hxsg.index.service;

import com.hxsg.CommonUtil.CommonException;
import com.hxsg.po.NewRole;
import com.hxsg.po.RoleNewShuXing;

import java.util.List;

/**
 * Created by Administrator on 2017/9/26 0026.
 */
public interface Cocos2dRoleInitService<T> {
    /**
     * 查询主角抗性
     * @param key
     * @return
     * @throws CommonException
     */
     List<RoleNewShuXing> getShuxing(String key) throws CommonException;

    /**
     * 查询主角信息
     * @param key
     * @return
     * @throws CommonException
     */
     NewRole  getRoleDetail(String key)throws CommonException;
}
