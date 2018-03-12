package com.hxsg.admin.service.impl;

import com.hxsg.Dao.IHxsgBaseDao;
import com.hxsg.admin.service.Cocos2dHxsgAdmin;
import com.hxsg.vo.ColumnAndComment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by dlf on 2016/10/14.
 */
@Service("Cocos2dHxsgAdmin")
public class Cocos2dHxsgAdminImpl implements Cocos2dHxsgAdmin {
    @Autowired
    IHxsgBaseDao ihxsgbasedao;
    @Override
    public String[][] getTableColumn(String database,String tableName) {
        String[][] arr=null;
        try {
            List<ColumnAndComment> li=ihxsgbasedao.getTableColumn(database, tableName);
            arr= new String[2][li.size()];
            for(int i=0;i<li.size();i++){

                    arr[0][i]=li.get(i).getColumnName();//列名
                    arr[1][i]=li.get(i).getColumnComment();//注释


            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return arr;
    }

    @Override
    public List<Map<String, Object>> getTableValue(Map<String, Object> mp) {
        return ihxsgbasedao.mybatisQueryPage(mp);
    }

    @Override
    public Integer totalSize(Map<String, Object> mp) {
        return ihxsgbasedao.totalsize(mp);
    }

    @Override
    public int insertEverythingToTableName(Map<String, Object> name) {
        return ihxsgbasedao.insertEverythingToTableName(name);
    }

    @Override
    public int updateEverythingToTableName(Map<String, Object> name) {
        return ihxsgbasedao.updateTableToEverthing(name);
    }

    @Override
    public int deleteToEverthing(Map<String, Object> mp) {
        return ihxsgbasedao.deleteToEverthing(mp);
    }

    @Override
    public List<Map<String, Object>> selectEverythingToTableName(Map<String, Object> name) {
        return ihxsgbasedao.selectEverythingToTableName(name);
    }
}
