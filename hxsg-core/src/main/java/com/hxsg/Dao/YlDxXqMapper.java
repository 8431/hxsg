package com.hxsg.Dao;

import com.hxsg.po.YlDxXq;

import java.util.List;

public interface YlDxXqMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(YlDxXq record);

    List<YlDxXq> getAllByNum(Integer num);
    Integer SumAllByJin(YlDxXq record);
    Integer SumAllByYin(YlDxXq record);
    Integer YanZhiByYin(Integer roleid);
    Integer YanZhiByJin(Integer roleid);
    List<YlDxXq>TenWinRole(Integer num);
    List<YlDxXq>TenWinRoleJ(Integer num);
    List<YlDxXq>selectAll(YlDxXq record);
    int insertSelective(YlDxXq record);
    List<YlDxXq> touzhuhistory(Integer roleid);

    List<YlDxXq>winyinbang();

    /**
     * 查询赌场赚钱排行榜
     * @return
     */
    List<YlDxXq>winJinBang();
    YlDxXq selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(YlDxXq record);

    int updateByPrimaryKey(YlDxXq record);

    /**
     * 根据输赢结果和角色ID分组查询
     * @param num
     * @return
     */
    List<YlDxXq>queryGroupBy(Integer num);
}