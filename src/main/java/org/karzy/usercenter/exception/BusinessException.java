package org.karzy.usercenter.exception;

import org.karzy.usercenter.common.ErrorCode;

import java.io.Serial;

/**
 * @author karzy
 */
public class BusinessException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 6901688078274037402L;
    private final String description;
    private final Integer code;

    public BusinessException(String message, String description, Integer code) {
        super(message);
        this.description = description;
        this.code = code;
    }
    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getMsg());
        this.description = errorCode.getDescription();
        this.code = errorCode.getCode();
    }
    public BusinessException(ErrorCode errorCode, String description) {
        super(errorCode.getMsg());
        this.description = description;
        this.code = errorCode.getCode();
    }

    public String getDescription() {
        return description;
    }

    public Integer getCode() {
        return code;
    }
}
