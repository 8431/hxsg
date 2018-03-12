package com.hxsg.Dao;

import com.hxsg.po.SkillDescribe;

import java.util.List;

public interface SkillDescribeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SkillDescribe record);

    int insertSelective(SkillDescribe record);

    SkillDescribe selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SkillDescribe record);

    int updateByPrimaryKey(SkillDescribe record);

    /**
     * 根据职业查询技能
     * @param zhiye
     * @return
     */
    List<SkillDescribe> querySkillByZhiYe(String zhiye,String xiebie1,String xiebie2) throws Exception;

    /**
     * 根据任意字段查询表
     * @param s
     * @return
     * @throws Exception
     */
    List<SkillDescribe> selectAll(SkillDescribe s) throws Exception;
}