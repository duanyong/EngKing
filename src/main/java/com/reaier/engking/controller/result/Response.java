package com.reaier.engking.controller.result;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response extends BaseResult {
    public static Response builder() {
        return new Response();
    }

    public static Response isOk() {
        Response result = new Response();

        result.code     = RootSuccessCode;
        result.message  = RootSuccessMessage;

        return result;
    }

    public static Response isBad() {
        Response result = new Response();

        result.code     = RootErrorCode;
        result.message  = RootErrorMessage;

        return result;
    }

    public static Response noLogin() {
        Response result = new Response();

        result.code     = RootErrorCode;
        result.message  = RootErrorMessage;

        return result;
    }

    public static Response fail(String message) {
        Response result = new Response();

        result.code     = RootErrorCode;
        result.message  = message;

        return result;
    }
}
