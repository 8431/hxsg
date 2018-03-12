package com.hxsg.CommonUtil;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/9/23 0023.
 */
public class SimpleResult extends HashMap<String, Object> {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    public static String SUCCESS = "000";
    public static String ERROR = "001";
    public static SimpleResult success() {
        return new SimpleResult(SimpleCode.SUCCESS);
    }
    public static SimpleResult error() {
        return new SimpleResult(SimpleCode.SUCCESS);
    }
    public static SimpleResult error(String code,String message) {
        return new SimpleResult(code, message);
    }

    public SimpleResult() {
    }

    public SimpleResult(SimpleCode simpleCode) {
        this(simpleCode.getCode(), simpleCode.getMessage());
    }

    public SimpleResult(String code, String message) {
        put("data", new LinkedHashMap<String, Object>());
        setCode(code);
        setMessage(message);
    }

    public static SimpleResult fromError(SimpleError e) {
        return new SimpleResult(e.getCode(), e.getMessage());
    }



    public void setCode(String code) {
        put("code", code);
    }

    public String getMessage() {
        return (String) get("message");
    }

    public void setMessage(String message) {
        put("message", message);
    }

    @SuppressWarnings("unchecked")
    public SimpleResult putData(String key, Object value) {
        ((LinkedHashMap<String, Object>) get("data")).put(key, value);
        return this;
    }

    @SuppressWarnings("unchecked")
    public Object getData(String key) {
        return ((LinkedHashMap<String, Object>) get("data")).get(key);
    }

    @SuppressWarnings("unchecked")
    public SimpleResult putDataAll(Map<? extends String, ?> map) {
        ((LinkedHashMap<String, Object>) get("data")).putAll(map);
        return this;
    }
}
