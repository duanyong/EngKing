package com.reaier.engking.controller.status;

import lombok.Getter;

@Getter
public enum ExceptionStatus implements ApiStatus {
    ExceptionStatus(10000, "发生错误");

    private final int code;       // 操作代码
    private final String message; // 消息提示


    ExceptionStatus(final int code, final String message) {
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
