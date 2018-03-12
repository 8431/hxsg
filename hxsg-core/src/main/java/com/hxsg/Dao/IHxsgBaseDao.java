package com.hxsg.Dao;

/*
* 对任意表操作
*
* */

import com.hxsg.vo.ColumnAndComment;
import javax.persistence.Column;
import java.util.List;
import java.util.Map;

public interface IHxsgBaseDao {
    /*
     * 动态MAp
     *根据任意表的信息查询任意表
     */
    List<Map<String, Object>> selectEverythingToTableName(Map<String, Object> name);

    /*
    * 根据任意表中任意字段update任意表
    *
    */
    int updateTableToEverthing(Map<String, Object> name);

    /*
   * 查询任意表中共有多少条记录
   *
   */
    int totalsize(Map<String, Object> name);

    /*
    * 根据任意字段插入到任意表中
    *
    */
    int insertEverythingToTableName(Map<String, Object> name);

    /**
     * 获取任意表的列名和注释
     * @param tableName
     * @return
     */
    List<ColumnAndComment> getTableColumn(String database,String tableName);

    /**
     * 对任意表分页
     * @param mp
     * @return
     */
    List<Map<String,Object>> mybatisQueryPage(Map<String,Object> mp);

    /**
     * 删除数据
     * @param mp
     * @return
     */
   int deleteToEverthing (Map<String,Object> mp);

    List<Map<String,Object>> exuSql(Map<String,Object> mp)throws Exception;
}