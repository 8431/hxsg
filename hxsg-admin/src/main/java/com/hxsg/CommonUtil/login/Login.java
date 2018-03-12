package com.hxsg.CommonUtil.login;

import com.hxsg.po.Acount;
import com.hxsg.po.NewRole;

import java.util.List;

/**
 * Created by dlf on 2017/2/22.
 */
public class Login {
    @Override
    public boolean equals(Object o) {
        Login ln= (Login) o;
        if(roleId.equals(ln.getRoleId())){
            return true;
        }else{
            return false;
        }
    }

    public Integer roleId;
    public String  roleName;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Acount acount;
    public List<NewRole>  liRole;

    public Acount getAcount() {
        return acount;
    }

    public void setAcount(Acount acount) {
        this.acount = acount;
    }

    public List<NewRole> getLiRole() {
        return liRole;
    }

    public void setLiRole(List<NewRole> liRole) {
        this.liRole = liRole;
    }
}
