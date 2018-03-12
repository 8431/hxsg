package com.hxsg.Dao;

import com.hxsg.po.ChibiYazhuDetail;

import java.util.List;

public interface ChibiYazhuDetailMapper {
    int deleteByPrimaryKey(Integer id)throws Exception;

    int insert(ChibiYazhuDetail record)throws Exception;

    int insertSelective(ChibiYazhuDetail record)throws Exception;

    ChibiYazhuDetail selectByPrimaryKey(Integer id)throws Exception;

    int updateByPrimaryKeySelective(ChibiYazhuDetail record)throws Exception;

    int updateByPrimaryKey(ChibiYazhuDetail record)throws Exception;

    /**
     * 统计某期押注金额详情
     * @param num
     * @return
     */
    List<ChibiYazhuDetail> queryYzZhuDetail(Integer num)throws Exception;

    /**
     * 根据角色ID和期数统计押注情况
     * @param num
     * @return
     */
    List<ChibiYazhuDetail> queryPlayerYaZhuDetail(Integer num)throws Exception;

    /**
     * 查询玩家当期押注总额
     * @param roleid
     * @param num
     * @return
     */
    ChibiYazhuDetail queryPlayerTotal(Integer roleid,Integer num)throws Exception;

    /**
     * 赤壁声援记录
     * @return
     * @throws Exception
     */
    List<ChibiYazhuDetail> queryYaZhuRecord(Integer roleid)throws Exception;

    /**
     * 查询奖励排行
     * @return
     * @throws Exception
     */
    List<ChibiYazhuDetail> queryTotalRecord()throws Exception;
    /**
     * 战利品排行
     * @return
     * @throws Exception
     */
    List<ChibiYazhuDetail> queryRecordforNum(Integer num)throws Exception;


    List<ChibiYazhuDetail> queryYzDetail(Integer num,Integer rolid)throws Exception;

}