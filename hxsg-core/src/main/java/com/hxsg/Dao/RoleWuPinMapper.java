package com.hxsg.Dao;

import com.hxsg.po.RoleWuPin;

import java.util.List;

public interface RoleWuPinMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RoleWuPin record);

    int insertSelective(RoleWuPin record);

    RoleWuPin selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RoleWuPin record) throws RuntimeException;

    int updateByPrimaryKey(RoleWuPin record);
    List<RoleWuPin> selectAll(RoleWuPin record);

    /**
     * 查询杂物
     * @param record
     * @return
     */
    List<RoleWuPin> queryZaWu(RoleWuPin record);
}