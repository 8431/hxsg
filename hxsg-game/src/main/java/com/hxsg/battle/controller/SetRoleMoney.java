package com.hxsg.battle.controller;

import com.hxsg.Dao.RoleMapper;
import com.hxsg.po.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by dlf on 2016/1/29.
 */
@Service
public class SetRoleMoney {

    @Autowired
    private static RoleMapper rm;

    public synchronized static Integer setmoney(Integer roleid, Integer money) {

        Role re = rm.selectByPrimaryKey(roleid);
        Integer result = null;
        int roleyin = re.getYin();
        if (roleyin >= money) {
            //扣费
            re.setYin(roleyin - money);
            rm.updateByPrimaryKeySelective(re);
            result = 1;
        } else {
            result = 0;
        }

        return result;
    }

}