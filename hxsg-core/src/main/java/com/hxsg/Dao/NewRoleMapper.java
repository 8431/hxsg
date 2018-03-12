package com.hxsg.Dao;

import com.hxsg.po.NewRole;

import java.util.List;

public interface NewRoleMapper {
    int deleteByPrimaryKey(Integer id)throws Exception;

    int insert(NewRole record)throws Exception;

    int insertSelective(NewRole record)throws Exception;

    NewRole selectByPrimaryKey(Integer id)throws Exception;

    int updateByPrimaryKeySelective(NewRole record) throws  Exception;

    int updateByPrimaryKey(NewRole record)throws Exception;

    /**
     * 根据任意字段查询匹配
     * @param record
     * @return
     */
    List<NewRole> selectAll(NewRole record)throws Exception;
    /**
     * 角色属性查询大视图
     * @param id
     * @return
     * @throws Exception
     */
    NewRole queryTotalShuXingToRole(Integer id) throws Exception;

    /**
     * 联合查询气血，金钱，经验
     * @param id
     * @return
     * @throws Exception
     */
    NewRole queryRoleMsgQx(Integer id) throws Exception;
    /**
     * 模糊查询角色名
     * @param name
     * @return
     */
    List<NewRole> queryFriends(String name);
    List<NewRole> queryRoleNameByIdList(List<NewRole> li)throws Exception;
}