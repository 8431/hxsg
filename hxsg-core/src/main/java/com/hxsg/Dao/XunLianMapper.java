package com.hxsg.Dao;

import com.hxsg.po.XunLian;

import java.util.List;

public interface XunLianMapper {
    int deleteByPrimaryKey(Integer id)throws RuntimeException;

    int insert(XunLian record)throws RuntimeException;

    int insertSelective(XunLian record)throws RuntimeException;

    XunLian selectByPrimaryKey(Integer id)throws RuntimeException;

    int updateByPrimaryKeySelective(XunLian record) throws RuntimeException;

    int updateByPrimaryKey(XunLian record)throws RuntimeException;
   List<XunLian> selectAll(XunLian record)throws RuntimeException;
    List<XunLian> queryTraining(String record,String type)throws RuntimeException;
}