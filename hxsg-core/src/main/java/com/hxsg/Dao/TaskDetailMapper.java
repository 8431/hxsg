package com.hxsg.Dao;

import com.hxsg.po.TaskDetail;

import java.util.List;

public interface TaskDetailMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TaskDetail record);

    int insertSelective(TaskDetail record);

    TaskDetail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TaskDetail record);

    int updateByPrimaryKey(TaskDetail record);
    List<TaskDetail> selectAll(TaskDetail d) throws Exception;
}