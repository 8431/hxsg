package com.hxsg.Dao;

import com.hxsg.po.RoleNewZhuangBei;

import java.util.List;

public interface RoleNewZhuangBeiMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RoleNewZhuangBei record);

    int insertSelective(RoleNewZhuangBei record);

    RoleNewZhuangBei selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RoleNewZhuangBei record);

    int updateByPrimaryKey(RoleNewZhuangBei record);

    /**
     * 查询角色的装备
     * @param roleid 角色ID
     * @param Type 是否被装备 0否1是
     * @return
     */
    List<RoleNewZhuangBei> queryRoleZhaungBei(Integer roleid,Integer Type);

}