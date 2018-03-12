package com.hxsg.wupin.service;
import com.hxsg.po.*;
import com.hxsg.vo.ZaWuDetailVo;
import com.hxsg.vo.ZhuangBeiDetailVo;

import java.util.List;

/**
 * Created by dlf on 2015/12/31.
 */
public interface cocos2dWuPinService {
    /**
     * 查询角色物品
     *
     * @param rw
     * @return
     * @throws Exception
     */
    public List<RoleWuPin> queryRoleWupin(RoleWuPin rw) throws Exception;

    /**
     * 根据主键ID查询杂物--药品描述信息
     *
     * @param id
     * @return
     * @throws Exception
     */
    public ZaWuMiaoShu queryWuPinDescribe(Integer id) throws Exception;

    /**
     * 查询角色拥有的宝石
     *
     * @param roleid
     * @return
     * @throws Exception
     */
    public List<ZaWuDetailVo> queryWuPinByBaoShi(Integer roleid,String rolename) throws Exception;

    /**
     * 查询宝石描述信息
     *
     * @param id
     * @return
     * @throws Exception
     */
    public ZaWuDetailVo queryBaoShiDescribe(Integer id) throws Exception;

    /**
     * 宝石-砸开矿石
     *
     * @return
     * @throws Exception
     */
    public String userMineral(RoleWuPin rn, String roleName) throws Exception;

    /**
     * 装备详情查看
     *
     * @return
     * @throws Exception
     */
    public ZhuangBeiDetailVo queryZhuangBeiDetail(ZhuangBeiDetailVo zb, String rolename) throws Exception;

    /**
     * 杂物-药品-矿石详情查看
     *
     * @param zw
     * @return
     * @throws Exception
     */
    public ZaWuDetailVo queryZaWuDetail(ZaWuDetailVo zw) throws Exception;

    /**
     *将宝石镶嵌到装备上
     * @param roleid
     * @param bsId
     * @param zbId
     * @return
     * @throws Exception
     */


    public String mosaicGemtoZhuangBei(Integer roleid,Integer bsId,Integer zbId) throws Exception;

    /**
     * 获取可以被宝石装备的装备列表
     *
     * @param wupinId
     * @param roleId
     * @return
     * @throws Exception
     */
    public List<RoleZhuangbeiMs> getBaoShiByZhaungbei(Integer wupinId, Integer roleId) throws Exception;

    /**
     * 使用副将心法书
     * @param wupinId
     * @param roleId
     * @return
     * @throws Exception
     */
    public String UserXinFaShu(Integer fuId,Integer wupinId, Integer roleId,Integer nums) throws Exception;
    /**
     * 查询物品-装备列表
     * @param roleid
     * @param type 0否1装备
     * @return
     * @throws Exception
     */
    public   List<RoleNewZhuangBei> queryWuPinZhuangBei(Integer roleid,Integer type) throws Exception;

    /**
     * 消耗道具
     * @param roleid
     * @param wupinId
     * @param num
     * @return
     */
    public Boolean xiaoHaoWuPin(Integer roleid,Integer wupinId,Integer num)throws Exception;




  }
