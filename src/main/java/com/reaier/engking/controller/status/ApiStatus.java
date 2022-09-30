package com.reaier.engking.controller.status;

public interface ApiStatus {
    int SUCCESS_CODE        = 0;
    String SUCCESS_MESSAGE  = "Success";

    int FAIL_CODE       = 1;
    String FAIL_MESSAGE = "未知错误";

    int PARAMETER_VALIDATE_ERROR_CODE       = 10;
    String PARAMETER_VALIDATE_ERROR_MESSAGE = "请求的参数验证失败，请修改后再试";

    int METHOD_NOT_SUPPORT_CODE         = 11;
    String METHOD_NOT_SUPPORT_MESSAGE   = "请求方式错误，请修改后再试";

    int DATABASE_ERROR_CODE         = 20;
    String DATABASE_ERROR_MESSAGE   = "数据在存储时出错，请稍候再试";

    int KEY_IS_EMPTY_CODE       = 50;
    String KEY_IS_EMPTY_MESSAGE = "指定的搜索关键字，否则无法搜索对应数据";

    int getCode();

    String getMessage();
}
