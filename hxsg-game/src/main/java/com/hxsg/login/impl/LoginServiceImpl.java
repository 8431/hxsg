package com.hxsg.login.impl;

import com.hxsg.login.LoginService;

import com.hxsg.po.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by dlf on 2016/1/1.
 */
@Service("LoginService")
public class LoginServiceImpl implements LoginService{

    @Autowired
    com.hxsg.Dao.RoleMapper rm;
    @Override
    public List<Role> YzLogin(Role record) {
        return rm.login(record);
    }
}
