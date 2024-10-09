package org.karzy.usercenter.exception;

import lombok.extern.slf4j.Slf4j;
import org.karzy.usercenter.common.ErrorCode;
import org.karzy.usercenter.common.Rsg;
import org.karzy.usercenter.common.RsgUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author karzy
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler()
    public Rsg businessExceptionHandler(BusinessException e){
        log.error("businessException" + e.getMessage(), e);
        return RsgUtils.error(e.getCode(), e.getMessage(), e.getDescription());
    }
    @ExceptionHandler()
    public Rsg runtimeExceptionHandler(RuntimeException e){
        log.error("runtimeException", e);
        return RsgUtils.error(ErrorCode.SYSTEM_ERROR, e.getMessage(), "");
    }
}
