package com.hxsg.po;

import java.io.Serializable;
import java.util.Date;

public class RoleFriends implements Serializable {
    private Integer id;

    private Integer roleid;

    private Integer friendid;

    private Date data;

    private String lahei;

    private String status;

    private String type;

    private String rolename;

    private String friendname;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoleid() {
        return roleid;
    }

    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }

    public Integer getFriendid() {
        return friendid;
    }

    public void setFriendid(Integer friendid) {
        this.friendid = friendid;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getLahei() {
        return lahei;
    }

    public void setLahei(String lahei) {
        this.lahei = lahei == null ? null : lahei.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename == null ? null : rolename.trim();
    }

    public String getFriendname() {
        return friendname;
    }

    public void setFriendname(String friendname) {
        this.friendname = friendname == null ? null : friendname.trim();
    }
}