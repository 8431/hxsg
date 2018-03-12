package com.hxsg.erroptype;

/**
 * Created by Administrator on 2017/9/23 0023.
 */
public enum  LoginErrorType {

    SUCCESS("000", "成功"),
    FAIL("001", "失败"),
    ERROR("002", "登录系统异常"),
    CREATEROLE("003", "创建角色页面"),
    INDEX("004", "主界面"),
    LOGINED("204", "重复登录"),
    SELECTROLE("005", "选择角色"),
    BPSUCCESS("success", "true"),
    BPMESSAGE("message", "上传成功"),
    BPERROR("success", "true"),
    BPERRORMESSAGE("message", "上传成功");

    private String  code;
    private String message;

    LoginErrorType(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
