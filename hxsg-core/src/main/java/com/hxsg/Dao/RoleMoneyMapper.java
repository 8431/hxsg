package com.hxsg.Dao;

import com.hxsg.po.RoleMoney;

public interface RoleMoneyMapper {
    int deleteByPrimaryKey(Integer id)throws Exception;

    int insert(RoleMoney record)throws Exception;
    RoleMoney selectByRoleid (Integer id)throws RuntimeException;;
    int insertSelective(RoleMoney record)throws RuntimeException;

    RoleMoney selectByPrimaryKey(Integer id)throws Exception;

    int updateByPrimaryKeySelective(RoleMoney record) throws  RuntimeException;

    int updateByPrimaryKey(RoleMoney record)throws RuntimeException;
}