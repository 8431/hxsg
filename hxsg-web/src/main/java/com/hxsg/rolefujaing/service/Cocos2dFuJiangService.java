package com.hxsg.rolefujaing.service;

import com.hxsg.vo.FuJiangDetailVo;
import com.hxsg.vo.PeiYangFuJiangVo;

/**
 * Created by dlf on 2015/12/31.
 */
public interface Cocos2dFuJiangService {
    /**
     * 加载副将培养界面
     * @param fuid
     * @param roleid
     * @return
     */
    public PeiYangFuJiangVo loadPeiYangFuJiang(Integer fuid,Integer roleid)throws Exception;

    /**
     * 加载角色副将页面
     * @param roleid 角色ID
     * @param fuid 副将ID
     * @return
     * @throws Exception
     */
    public FuJiangDetailVo loadRoleFuJiang(Integer roleid,Integer fuid)throws Exception;

}
