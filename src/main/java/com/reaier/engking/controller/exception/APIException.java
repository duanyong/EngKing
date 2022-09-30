package com.reaier.engking.controller.exception;

import com.reaier.engking.controller.status.ApiStatus;
import com.reaier.engking.controller.status.ExceptionStatus;
import lombok.Getter;

@Getter
public class APIException extends RuntimeException {
    private final ApiStatus status;

    public APIException(Exception e) {
        super(e);

        this.status = ExceptionStatus.ExceptionStatus;
    }

    // 手动设置异常
    public APIException(ApiStatus status, String message) {
        super(message);

        this.status = status;
    }

    // 默认异常使用APP_ERROR状态码
    public APIException(final String message) {
        this(ExceptionStatus.ExceptionStatus, message);
    }

    public APIException(final ApiStatus status) {
        this(status, ExceptionStatus.ExceptionStatus.getMessage());
    }
}
