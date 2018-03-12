package com.hxsg.po;

import java.io.Serializable;
import java.util.Date;

public class Skill implements Serializable {

    private Integer id;

    private Integer shuliandu;

    private String skillname;

    private String level;

    private Integer rfid;

    private Date date;

    private Integer describeid;

    private Integer type;

    private String rolename;

    private String status;

    private Integer fulevel;

    private Integer skillid;
    private Integer roleLevel;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getShuliandu() {
        return shuliandu;
    }

    public void setShuliandu(Integer shuliandu) {
        this.shuliandu = shuliandu;
    }

    public String getSkillname() {
        return skillname;
    }

    public void setSkillname(String skillname) {
        this.skillname = skillname == null ? null : skillname.trim();
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level == null ? null : level.trim();
    }

    public Integer getRfid() {
        return rfid;
    }

    public void setRfid(Integer rfid) {
        this.rfid = rfid;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getDescribeid() {
        return describeid;
    }

    public void setDescribeid(Integer describeid) {
        this.describeid = describeid;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename == null ? null : rolename.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Integer getFulevel() {
        return fulevel;
    }

    public void setFulevel(Integer fulevel) {
        this.fulevel = fulevel;
    }

    public Integer getSkillid() {
        return skillid;
    }

    public void setSkillid(Integer skillid) {
        this.skillid = skillid;
    }

    public Integer getRoleLevel() {
        return roleLevel;
    }

    public void setRoleLevel(Integer roleLevel) {
        this.roleLevel = roleLevel;
    }
}