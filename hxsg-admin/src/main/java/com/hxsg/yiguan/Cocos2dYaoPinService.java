package com.hxsg.yiguan;

import com.hxsg.CommonUtil.CommonException;

import java.util.List;
import java.util.Map;

/**
 * Created by dlf on 2018/1/17 0017.
 * Email 1429264916@qq.com
 */
public interface Cocos2dYaoPinService {
    /**
     * 查询药品详情
     * @param id
     * @return
     * @throws CommonException
     */
    Map<String,Object>  queryYaoPinDetail(String id) throws CommonException;

    /**
     * 购买药品
     * @param id
     * @return
     * @throws CommonException
     */
    Map<String,Object>  buyYao(String id,String num,String roleid) throws CommonException;

    /**
     * 购买物品
     * @param money
     * @param roleid
     * @param type
     * @return
     * @throws CommonException
     */
     String xiaofei(Integer money, String roleid, String type) throws CommonException;

    /**
     * 查询角色拥有的药品
     * @param roleid
     * @return
     * @throws CommonException
     */
     List<Map<String,Object>> queryRoleYao(String roleid)throws CommonException;

}
