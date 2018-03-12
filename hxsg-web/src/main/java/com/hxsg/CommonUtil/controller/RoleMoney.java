package com.hxsg.CommonUtil.controller;

import com.hxsg.Dao.RoleMapper;
import com.hxsg.po.Role;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;

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
