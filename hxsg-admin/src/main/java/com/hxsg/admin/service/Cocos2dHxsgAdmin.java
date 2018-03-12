package com.hxsg.admin.service;

import java.util.List;
import java.util.Map;

/**系统公告推送
 * Created by dlf on 2016/10/14.
 */

public interface Cocos2dHxsgAdmin {
    /**
     * 获取表的列
     * @param tableName
     * @return
     */
   public  String[][] getTableColumn(String database,String tableName);

    /**
     *
     * @param mp
     * @return
     */
   public List<Map<String,Object>> getTableValue(Map<String,Object> mp);
   public Integer totalSize(Map<String,Object> mp);
    /*
    * 根据任意字段插入到任意表中
    *
    */
    int insertEverythingToTableName(Map<String, Object> name);
    /*
        * 根据任意表中任意字段update任意表
        *
        */
    int updateEverythingToTableName(Map<String, Object> name);
    /**
     * 删除数据
     * @param mp
     * @return
     */
    int deleteToEverthing (Map<String,Object> mp);
    /*
    * 动态MAp
    *根据任意表的信息查询任意表
    */
    List<Map<String, Object>> selectEverythingToTableName(Map<String, Object> name);

}
