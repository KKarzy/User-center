package org.karzy.usercenter.common;

/**
 * @author karzy
 */
public enum ErrorCode {
    SUCCESS(0,"OK",""),
    PARAMS_ERROR(40000,"请求参数错误",""),
    NULL_ERROR(40001,"请求数据为空",""),
    NO_AUTH(40100,"无权限",""),
    NOT_LOGIN(40101,"未登录",""),
    SYSTEM_ERROR(50000,"系统内部异常","");

    private final Integer code;
    private final String msg;
    private final String description;

    ErrorCode(Integer code, String msg, String description) {
        this.code = code;
        this.msg = msg;
        this.description = description;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public String getDescription() {
        return description;
    }
}
