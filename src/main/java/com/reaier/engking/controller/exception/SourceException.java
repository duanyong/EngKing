package com.reaier.engking.controller.exception;

import com.reaier.engking.controller.status.ApiStatus;

public enum SourceException implements ApiStatus {
    THE_SOURCE_HAS_EXIST(101002, "指定的内容已存在"),
    THE_SOURCE_HAS_NOT_EXIST(101004, "指定的内容不存在"),
    THE_FEED_CREATED_EXCEPTION(101010, "创建超粉数据时异常，请稍候再试"),
    THE_FEED_MODIFY_EXCEPTION(101011, "修改超粉数据时异常，请稍候再试");

    // 操作代码
    private final int code;
    // 消息提示
    private final String message;

    SourceException(final int code, final String message) {
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
