package com.hxsg.dao;

import com.hxsg.CommonUtil.CommonException;
import com.hxsg.vo.PkRoleVo;

import java.util.List;
import java.util.Map;

/**
 * Created by dlf on 2017/11/14 0014.
 * Email 1429264916@qq.com
 */
public interface PkVoService {
    List<PkRoleVo> getPkRoleVo(String key,String state) throws Exception;

    /**
     * 构建野怪数据
     * @param level
     * @param name
     * @param key
     * @return
     * @throws CommonException
     */
    Map<String,Object> getYeGuaiData(String level,String name,String key) throws CommonException;

    /**
     * 处理pk指令
     * @param data
     * @return
     * @throws CommonException
     */
    Map<String,Object> handlePkData(String data) throws CommonException;

    /**
     * 使用药品
     * @param id
     * @param key
     * @throws CommonException
     */
    void usrYao(String id,String key) throws CommonException;

    //查询当前玩家是否有pk
    String queryPk(String key) throws CommonException;


}
