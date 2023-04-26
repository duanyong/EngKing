package com.reaier.engking.controller.exception;

import com.reaier.engking.controller.status.ApiStatus;

public enum WordException implements ApiStatus {
    THE_WORD_HAS_EXIST(101102, "指定的单词已存在"),

    THE_WORD_IS_EMPTY(101103, "指定的单词为空"),
    THE_WORD_HAS_NOT_EXIST(101104, "指定的单词不存在"),
    THE_WORD_CREATED_EXCEPTION(101110, "创建单词数据时异常，请稍候再试"),
    THE_WORD_MODIFY_EXCEPTION(101111, "修改单词数据时异常，请稍候再试");

    // 操作代码
    private final int code;
    // 消息提示
    private final String message;

    WordException(final int code, final String message) {
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
