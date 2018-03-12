package com.hxsg.CommonServer;

import com.hxsg.po.RoleFujiang;
import com.hxsg.po.RoleWuPin;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/9/18 0018.
 */
public interface CommonServer {
    /**
     * 使用药品
     * @param roleid
     * @param name
     * @return
     */
    Map<String,Object> userRoleYao(String roleid,String name) throws Exception;

    /**
     * 获得道具
     * @param roleid
     * @param rolename
     * @param i
     * @return
     * @throws Exception
     */
    List<RoleWuPin> getDaoJu(Integer roleid, String rolename, int i) throws Exception;

    /**
     * 副将获取经验
     * @param fuid
     * @param jyz
     * @return
     * @throws Exception
     */
    boolean fuJiangLevel(Integer fuid, Integer jyz) throws Exception;
    boolean  expConversionLevel(Integer roleId, Integer jyz)throws Exception;

}
