package com.reaier.engking.controller.result;

import com.reaier.core.utils.JsonUtils;
import com.reaier.engking.controller.result.page.Pagebar;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;

import java.util.List;

public class Response<T> extends BaseResult {
    private transient final Logger logger = LoggerFactory.getLogger(this.getClass());

    T data;                 //返回单个类数据
    List<T> list;           //返回列表
    Pagebar pagebar;        //列表的分布数据

    public Response(int code, String message) {
        super(code, message);
    }

    public static Response builder() {
        return new Response(RootSuccessCode, RootSuccessMessage);
    }

    public static String isOk() {
        Response response = Response.builder();

        response.code       = RootSuccessCode;
        response.message    = RootSuccessMessage;

        return JsonUtils.toJsonWithGson(response);
    }

    public static String isBad() {
        Response response = Response.builder();

        response.code     = RootErrorCode;
        response.message  = RootErrorMessage;

        return JsonUtils.toJsonWithGson(response);
    }

    public static String noLogin() {
        Response response = Response.builder();

        response.code     = RootErrorCode;
        response.message  = RootErrorMessage;

        return JsonUtils.toJsonWithGson(response);
    }

    public static String fail(String message) {
        Response response = Response.builder();

        response.code     = RootErrorCode;
        response.message  = message;

        return JsonUtils.toJsonWithGson(response);
    }

    public static String data(Object data) {
        Response response = Response.builder();

        response.data       = data;

        return JsonUtils.toJsonWithGson(response);
    }

    public static String list(Page page) {
        Response response = Response.builder();

        response.list       = page.getContent();
        response.pagebar    = Pagebar.fromPageable(page);

        return JsonUtils.toJsonWithGson(response);
    }
}
