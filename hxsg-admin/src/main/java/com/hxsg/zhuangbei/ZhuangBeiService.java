package com.hxsg.zhuangbei;

import com.hxsg.CommonUtil.CommonException;
import com.hxsg.po.yaoping;

import java.util.List;
import java.util.Map;

/**
 * Created by dlf on 2016/1/1.
 */
public interface ZhuangBeiService {
    /**
     * 查询装备商店
     * @param type
     * @return
     * @throws CommonException
     */
    List<Map<String,Object>> queryZhuangBeiShop(String type) throws CommonException;

    /**
     * 购买武器
     * @param roleid
     * @param id
     * @return
     * @throws CommonException
     */
    String buyZhuangBei(Integer roleid,String id)throws CommonException;

}
