package com.hxsg.Dao;

import com.hxsg.po.RoleFujiang;

import java.util.List;

public interface RoleFujiangMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RoleFujiang record);
    List<RoleFujiang> getByRoleIdShux(Integer roleid, Integer shuxing);
    List<RoleFujiang> getByRoleIdStatus(RoleFujiang record);
    List<RoleFujiang> getByRoleIdShuxLevel(Integer roleid, Integer shuxing, Integer level1, Integer level2);
    List<RoleFujiang> getByRoleIdShuxStatus(RoleFujiang record);
    int insertSelective(RoleFujiang record);
    List<RoleFujiang>selectAll(RoleFujiang record);
    RoleFujiang selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RoleFujiang record);

    int updateByPrimaryKey(RoleFujiang record);
}