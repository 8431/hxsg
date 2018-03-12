package com.hxsg.battle.service;

import com.hxsg.po.WaBao;
import com.hxsg.po.WaBaoMiaoShu;

import java.util.List;

/**
 * Created by dlf on 2016/11/23.
 *
 */
public interface Cocos2dWaBaoService {
    /**
     * 消耗藏宝图，初始化玩家挖宝数据
     * @param roleid
     * @param num
     * @return
     * @throws Exception
     */
   public String initWaBao(Integer roleid,String roleName,String name,Integer num) throws Exception;

    /**
     * 加载挖宝界面
     * @param roleid
     * @return
     * @throws Exception
     */
    public WaBao loadWaBaoCanvas(Integer roleid,String roleName)throws Exception;

    /**
     * 点击挖宝
     * @param type
     * @param wb
     * @return
     * @throws Exception
     */
    public String startWaBao(Integer roleid,String roleName,String type,WaBao wb)throws Exception;

    /**
     * 继续挖宝
     * @param roleid
     * @param id
     * @return
     * @throws Exception
     */
    public Boolean nextWaBao(Integer roleid,Integer id)throws Exception;

    /**
     * 初始化加载挖宝描述信息
     * @return
     * @throws Exception
     */
    public List<WaBaoMiaoShu> getWaBaoMiaoShu ()throws Exception;

}
