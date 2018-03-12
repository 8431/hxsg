package com.hxsg.Dao;

import com.hxsg.po.RoleYao;

import java.util.List;
import java.util.Map;

public interface RoleYaoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RoleYao record);

    int insertSelective(RoleYao record);

    RoleYao selectByPrimaryKey(Integer id);
    List<RoleYao> selectByYaoIdRoleId(Integer roleid, Integer yaoid);
    Map<String,Object> selectByYaoNameRoleId(String roleid, String yaoname) throws Exception;
    List<RoleYao> selectRoleId(Integer roleid);
    int updateByPrimaryKeySelective(RoleYao record);

    int updateByPrimaryKey(RoleYao record);
}