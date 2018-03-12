package com.hxsg.controller;

import com.hxsg.po.NewRole;
import com.hxsg.vo.PkRoleVo;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by dlf on 2018/1/15 0015.
 * Email 1429264916@qq.com
 */
public class PkDataSa  implements Serializable{
    private Map<String, PkRoleVo> mp;
    private List<String>  uuidStr;
    private  String  roleAId;
    private  String  roleBId;
    private  String  pkUuid;
    private  List<PkRoleVo> roleA;
    private  List<PkRoleVo> roleB;
    private  List<PkRoleVo> totalAB;
    private  List<PkRoleVo>  fujiang;
    private Integer  start;
    private Integer  size;
    private NewRole newRole;

    public Map<String, PkRoleVo> getMp() {
        return mp;
    }

    public void setMp(Map<String, PkRoleVo> mp) {
        this.mp = mp;
    }

    public List<String> getUuidStr() {
        return uuidStr;
    }

    public void setUuidStr(List<String> uuidStr) {
        this.uuidStr = uuidStr;
    }

    public String getRoleAId() {
        return roleAId;
    }

    public void setRoleAId(String roleAId) {
        this.roleAId = roleAId;
    }

    public String getRoleBId() {
        return roleBId;
    }

    public void setRoleBId(String roleBId) {
        this.roleBId = roleBId;
    }

    public String getPkUuid() {
        return pkUuid;
    }

    public void setPkUuid(String pkUuid) {
        this.pkUuid = pkUuid;
    }

    public List<PkRoleVo> getRoleA() {
        return roleA;
    }

    public void setRoleA(List<PkRoleVo> roleA) {
        this.roleA = roleA;
    }

    public List<PkRoleVo> getRoleB() {
        return roleB;
    }

    public void setRoleB(List<PkRoleVo> roleB) {
        this.roleB = roleB;
    }

    public List<PkRoleVo> getTotalAB() {
        return totalAB;
    }

    public void setTotalAB(List<PkRoleVo> totalAB) {
        this.totalAB = totalAB;
    }

    public List<PkRoleVo> getFujiang() {
        return fujiang;
    }

    public void setFujiang(List<PkRoleVo> fujiang) {
        this.fujiang = fujiang;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public NewRole getNewRole() {
        return newRole;
    }

    public void setNewRole(NewRole newRole) {
        this.newRole = newRole;
    }
}
