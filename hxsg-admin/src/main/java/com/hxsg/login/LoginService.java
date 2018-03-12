package com.hxsg.login;

import com.hxsg.po.Role;
import com.hxsg.po.rolePass;

import java.util.List;

/**
 * Created by dlf on 2016/1/1.
 */
public interface LoginService {
    List<Role> YzLogin(Role record);

}
