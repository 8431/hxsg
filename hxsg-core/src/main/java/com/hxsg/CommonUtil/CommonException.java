package com.hxsg.CommonUtil;

import javax.xml.bind.annotation.XmlAttribute;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * Created by Administrator on 2017/9/23 0023.
 */

public class CommonException extends Exception {
    String code = "COM0000.INTERNAL.EXCEPTION";
    String desc = "Internal Exception";

    public CommonException() {
        super();
    }

    public CommonException(Throwable cause) {
        super(cause);
    }

    public CommonException(CommonException ce) {
        super(ce.getMessage(), ce.getCause());
        this.code = ce.getCode();
        this.desc = ce.getDesc();
    }

    public CommonException(String msg) {
        super(msg);
    }

    public CommonException(String code, String desc) {
        super();
        this.code = code;
        this.desc = desc;
    }

    public CommonException(String code, String desc, Throwable cause) {
        super(cause);
        this.code = code;
        this.desc = desc;
    }

    public CommonException(String code, String desc, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
        this.desc = desc;
    }

    @XmlAttribute
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @XmlAttribute
    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * 打印Trace信息
     *
     * @param e
     * @return
     */
    public static String getTrace(Exception e) {
        if (null == e)
            return null;
        ByteArrayOutputStream out = null;
        PrintStream print = null;
        try {
            out = new ByteArrayOutputStream();
            print = new PrintStream(out);
            e.printStackTrace(print);
            print.flush();
            out.flush();
            return out.toString();
        } catch (Exception e1) {
            return null;
        } finally {
            try {
                if (out != null) out.close();
            } catch (Exception ignore) {
            }
            if (print != null) print.close();
        }
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("CommonException{");
        if (code != null && !"".equals(code)) sb.append("code={").append(code).append("},");
        if (desc != null && !"".equals(desc)) sb.append("desc={").append(desc).append("},");
        if (this.getMessage() != null && !"".equals(this.getMessage()))
            sb.append("message={").append(this.getMessage()).append("},");
        if (this.getCause() != null && !"".equals(this.getCause()))
            sb.append("cause={").append(this.getCause().getMessage()).append("},");
        return sb.toString();
    }
}