package com.hxsg.vo;

import java.io.Serializable;

/**
 * Created by dlf on 2016/10/31.
 * (125级)吕布.武   战
 */
public class IndexFuJiangVo implements Serializable{

    private String name;//(125级)吕布.武
    private String status;//战
    private Integer roleId;//角色ID
    private Integer fuId;//副将ID

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getFuId() {
        return fuId;
    }

    public void setFuId(Integer fuId) {
        this.fuId = fuId;
    }
}
