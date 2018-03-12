package com.hxsg.Dao;

import com.hxsg.po.BaoShi;

import java.util.List;

public interface BaoShiMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BaoShi record);

    int insertSelective(BaoShi record);

    BaoShi selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BaoShi record);

    int updateByPrimaryKey(BaoShi record);
    List<BaoShi> selectAll(BaoShi record);
    /**
     * 通过主键查询是否被装备过的宝石
     * @param id
     * @param status 0否1是
     * @return
     */
    BaoShi selectBaoShiForStatus(Integer id,Integer status);

    /**
     * 查询物品宝石栏
     * @param roleid 角色ID
     * @return
     */
    List<BaoShi> queryWuPinByBaoShi(Integer roleid);
}