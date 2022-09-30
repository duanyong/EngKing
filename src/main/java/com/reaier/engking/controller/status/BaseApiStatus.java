package com.reaier.engking.controller.status;

import lombok.Getter;

@Getter
public enum BaseApiStatus implements ApiStatus {
    SUCCESS(SUCCESS_CODE, SUCCESS_MESSAGE),
    FAILED(FAIL_CODE, FAIL_MESSAGE),
    PARAMETER_VALIDATE_ERROR(PARAMETER_VALIDATE_ERROR_CODE, PARAMETER_VALIDATE_ERROR_MESSAGE),
    METHOD_NOT_SUPPORT_ERROR(METHOD_NOT_SUPPORT_CODE, METHOD_NOT_SUPPORT_MESSAGE),
    THE_DATABASE_HAS_ERROR(DATABASE_ERROR_CODE, DATABASE_ERROR_MESSAGE);

    // 操作代码
    private final int code;
    // 消息提示
    private final String message;


    BaseApiStatus(final int code, final String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public int getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
