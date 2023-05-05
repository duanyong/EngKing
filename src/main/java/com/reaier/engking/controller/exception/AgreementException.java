package com.reaier.engking.controller.exception;

import com.reaier.engking.controller.status.ApiStatus;

public enum AgreementException implements ApiStatus {
    THE_DATA_HAS_EXIST(101202, "指定的协议已存在"),
    THE_DATA_HAS_NOT_EXIST(101204, "指定的协议不存在"),
    THE_DATA_CREATED_EXCEPTION(101210, "创建协议数据时异常，请稍候再试"),
    THE_DATA_MODIFY_EXCEPTION(101211, "修改协议数据时异常，请稍候再试");

    // 操作代码
    private final int code;
    // 消息提示
    private final String message;

    AgreementException(final int code, final String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
