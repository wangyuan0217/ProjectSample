package com.trump.library_common.config;

/**
 * @author 王元_Trump
 * @time 2020/03/26 15:52
 * @desc
 */
public enum HttpResponseCode {
    RESPONSE_OK(1, "服务器返回期望数据"),
    PARSE_ERROR(10001, "解析异常"),
    NET_ERROR(10002, "无网络"),
    TOKEN_EXCEPTION(10003, "token异常"),
    UNKNOWN(10004, "未知错误");


    private int code;
    private String message;

    HttpResponseCode() {
    }

    HttpResponseCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
