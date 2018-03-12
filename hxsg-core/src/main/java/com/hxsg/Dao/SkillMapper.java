package com.hxsg.Dao;

import com.hxsg.po.Skill;

import java.util.List;

public interface SkillMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Skill record);

    int insertSelective(Skill record);

    Skill selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Skill record);

    int updateByPrimaryKey(Skill record);

    /**
     * 联合通过Id查询
     * @param id
     * @return
     */
    List<Skill> selectById(Integer id);
    /**
     * 新增sql联合副将表和技能表查询可以使用该技能书的人物或副将的技能
     * @param roleid 角色ID
     * @param level  技能书等级
     * @return
     * @throws Exception
     */
    List<Skill> queryNameForRole(Integer roleid,Integer level) throws  Exception;

    /**
     * 查询未参战状态下副将技能
     * @param roleid
     * @param level  技能书等级
     * @param fuId
     * @return
     * @throws Exception
     */
    List<Skill> querySkillForRole(Integer roleid,Integer level,Integer fuId) throws  Exception;

    /**
     * 根据任意条件相等去查询
     * @param s
     * @return
     * @throws Exception
     */
    List<Skill> selectAll(Skill s)throws  Exception;
}