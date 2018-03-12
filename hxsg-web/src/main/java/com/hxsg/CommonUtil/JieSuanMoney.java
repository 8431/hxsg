package com.hxsg.CommonUtil;

import com.hxsg.Dao.RoleMapper;
import com.hxsg.po.Role;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by dlf on 2016/1/25.
 */
public class JieSuanMoney {
    @Autowired
    static RoleMapper rm=null;
    public synchronized static void getMoney(Integer roleid,Integer rolemoney,String type){
        if(type.equals("yin")){
            Role re=rm.selectByPrimaryKey(roleid);
            Integer rolegetyin=re.getYin();
            if(rolemoney!=null&&rolegetyin>=rolemoney){
                re.setYin(rolegetyin-rolemoney);
                rm.updateByPrimaryKeySelective(re);
            }
        }
        if(type.equals("jin")){
            Role re=rm.selectByPrimaryKey(roleid);
            Integer rolegetjin=re.getJin();
            if(rolemoney!=null&&rolegetjin>=rolemoney){
                re.setJin(rolegetjin-rolemoney);
                rm.updateByPrimaryKeySelective(re);
            }
        }


    }
}
