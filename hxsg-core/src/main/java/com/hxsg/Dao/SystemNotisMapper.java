package com.hxsg.Dao;

import com.hxsg.po.SystemNotis;

import java.util.List;

public interface SystemNotisMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SystemNotis record);

    int insertSelective(SystemNotis record);

    SystemNotis selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SystemNotis record);

    int updateByPrimaryKey(SystemNotis record);

    /**
     * 查询系统公告前20
     * @return
     * @throws Exception
     */
    public List<SystemNotis> querySystemNotis() throws Exception;

}