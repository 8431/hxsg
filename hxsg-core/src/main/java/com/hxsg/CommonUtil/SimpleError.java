package com.hxsg.CommonUtil;

/**
 * Created by Administrator on 2017/9/23 0023.
 */

public class SimpleError extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String code;
    private String message;

    public static void e(SimpleCode simpleCode) {
        throw new SimpleError(simpleCode);
    }

    public SimpleError(SimpleCode simpleCode) {
        code = simpleCode.getCode();
        message = simpleCode.getMessage();
    }

    public SimpleError(String code) {
        this.code = code;
        this.message = "";
    }

    public SimpleError(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}