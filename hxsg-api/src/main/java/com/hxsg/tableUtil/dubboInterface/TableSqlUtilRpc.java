package com.hxsg.tableUtil.dubboInterface;

import java.util.Map;

/**
 * Created by dlf on 2016/10/14.
 * 在mysql数据库中对于任意表的增删改查接口
 */
public interface TableSqlUtilRpc {
    /**
     * mybatis插入数据分页查询
     *
     * @param mp 表名，待插入的字段数组，格式为：[["字段1","1"],["字段2","2"]......]
     * @param mp
     * @return
     * @throws Exception
     */
    public Object[] mybatisQueryPage(Map<String, Object> mp) throws Exception;

    /**
     * mybatis插入数据
     *
     * @param mp 表名，待插入的字段数组，格式为：[["字段1","1"],["字段2","2"]......]
     * @return true, false
     * @throws Exception
     */
    public boolean mybatisInsert(Map<String, Object> mp) throws Exception;

    /**
     * mybatis更新数据
     *
     * @param mp 表名，set数组["字段1","1"],["字段2","2"]......]where数组["字段1","1"],["字段2","2"]......]
     * @throws Exception
     * @returntrue,false
     */
    public boolean mybatisUpdate(Map<String, Object> mp) throws Exception;

    /**
     * mybatis删除
     *
     * @param mp 表名，数组["字段1","1"],["字段2","2"]......]where数组["字段1","1"],["字段2","2"]......]
     * @throws Exception
     * @returntrue,false
     */
    public boolean mybatisDelete(Map<String, Object> mp) throws Exception;

    /**
     * mybatis条件查询
     *
     * @param mp 表名，待插入的字段数组，格式为：[["字段1","1"],["字段2","2"]......]
     * @return
     * @throws Exception
     */
    public Object[] mybatisQuerySql(Map<String, Object> mp) throws Exception;

}
