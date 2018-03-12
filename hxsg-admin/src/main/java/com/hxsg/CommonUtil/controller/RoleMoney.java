package com.hxsg.CommonUtil.controller;

import com.hxsg.Dao.RoleMapper;
import com.hxsg.po.Role;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by dlf on 2016/3/11.
 */

public class RoleMoney  {
    @Autowired
     RoleMapper rm;

    public synchronized   void setMoney(Integer roleid ,Integer yin,Integer jin){
        Role re=rm.selectByPrimaryKey(roleid);
        if(re!=null&&re.getYin()>yin&&re.getJin()>jin){
            re.setYin(re.getYin()-yin);
            re.setJin(re.getJin() - jin);
            rm.updateByPrimaryKeySelective(re);
        }


    }
    public synchronized   void getMoney(Integer roleid ,Integer yin,Integer jin){
        Role re=rm.selectByPrimaryKey(roleid);
        if(re!=null){
            re.setYin(re.getYin()+yin);
            re.setJin(re.getJin()+jin);
            rm.updateByPrimaryKeySelective(re);
        }

    }


}
