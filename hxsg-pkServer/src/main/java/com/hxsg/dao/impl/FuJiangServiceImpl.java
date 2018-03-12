package com.hxsg.dao.impl;

import com.hxsg.CommonUtil.CommonException;
import com.hxsg.Dao.IHxsgBaseDao;
import com.hxsg.dao.FuJiangService;
import org.apache.ibatis.ognl.IntHashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dlf on 2017/11/14 0014.
 * Email 1429264916@qq.com
 */
@Service("FuJiangService")
public class FuJiangServiceImpl implements FuJiangService {
    @Autowired
    IHxsgBaseDao ihxsgbasedao;
    @Override
    public List<Map<String, Object>> queryStateFujiang(String roleid, String state) throws CommonException {
        Map<String,Object> mpParam=new HashMap();
        mpParam.put("roleid",roleid);
        mpParam.put("status",state);
        String sql="SELECT" +
                " r.*, round(" +
                "(" +
                "r.leve * r.chengzhang * r.chugong / 7" +
                ") + r.chugong * r.chengzhang * 0.5 + r.leve * r.chengzhang * r.gongJiDs * 0.2" +
                ") AS gong," +
                "round(" +
                "r.chengzhang * (r.suDuDs + r.chusu)" +
                ") AS sudu" +
                " FROM" +
                " role_fujiang r" +
                " WHERE" +
                " roleid = #{roleid}" +
                " AND STATUS = #{status}";
        mpParam.put("sql",sql);
        List<Map<String, Object>> result=null;
        try {
           result= ihxsgbasedao.exuSql(mpParam);
        } catch (Exception e) {
             throw new  CommonException("40000","查询随性副将异常");
        }
        return result;
    }
}
