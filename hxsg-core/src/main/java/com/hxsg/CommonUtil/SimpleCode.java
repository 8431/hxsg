package com.hxsg.CommonUtil;

/**
 * Created by Administrator on 2017/9/23 0023.
 */


    public enum SimpleCode {
        SUCCESS("000", "成功"),
        ERROR("001", "失败"),
        BPSUCCESS("success", "true"),
        BPMESSAGE("message", "上传成功"),
        BPERROR("success", "true"),
        BPERRORMESSAGE("message", "上传成功");
        private String  code;
        private String message;

        SimpleCode(String code, String message) {
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
