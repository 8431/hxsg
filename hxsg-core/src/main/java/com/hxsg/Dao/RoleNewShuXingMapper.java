package com.hxsg.Dao;

import com.hxsg.po.RoleNewShuXing;

import java.util.List;

public interface RoleNewShuXingMapper {
    int deleteByPrimaryKey(Integer id)throws Exception;

    int insert(RoleNewShuXing record)throws Exception;

    int insertSelective(RoleNewShuXing record)throws Exception;

    RoleNewShuXing selectByPrimaryKey(Integer id)throws Exception;

    int updateByPrimaryKeySelective(RoleNewShuXing record)throws Exception;

    int updateByPrimaryKey(RoleNewShuXing record)throws Exception;

    /**
     * 查询角色总抗性
     * @param id
     * @return
     * @throws Exception
     */
    List<RoleNewShuXing> queryRoleShuXing(Integer id, Integer status) throws Exception;
}