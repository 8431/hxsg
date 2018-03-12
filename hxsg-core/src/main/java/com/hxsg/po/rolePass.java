package com.hxsg.po;

import java.io.Serializable;
import java.util.List;

public class rolePass implements Serializable {
    private Integer id;

    private String name;

    private String password;

    private String supperpass;

    private String email;

    private String phone;

    private String mibao;

    private String answer;

    private Integer roleid;
    private List<Role> role;

    public void setRole(List<Role> role) {
        this.role = role;
    }

    public List<Role> getRole() {
        return role;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getSupperpass() {
        return supperpass;
    }

    public void setSupperpass(String supperpass) {
        this.supperpass = supperpass == null ? null : supperpass.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getMibao() {
        return mibao;
    }

    public void setMibao(String mibao) {
        this.mibao = mibao == null ? null : mibao.trim();
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer == null ? null : answer.trim();
    }

    public Integer getRoleid() {
        return roleid;
    }

    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }
}