package com.reaier.engking.advice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.reaier.engking.advice.annotation.NotControllerResponseAdvice;
import com.reaier.engking.controller.exception.APIException;
import com.reaier.engking.controller.response.ResponseVO;
import com.reaier.engking.controller.status.ExceptionStatus;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.lang.reflect.Type;

@RestControllerAdvice(annotations = {RestController.class, Controller.class})
public class ControllerResponseAdvice implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>>  converterType) {
        // response是ResponseVo类型，或者注释了 NotControllerResponseAdvice 都不进行包装
        if (ResponseVO.class.isAssignableFrom(returnType.getParameterType())) {
            return false;
        }

        if (returnType.hasMethodAnnotation(NotControllerResponseAdvice.class)) {
            return false;
        }

        if (HttpEntity.class.isAssignableFrom(returnType.getParameterType())) {
            return false;
        }

        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
//        // String类型不能直接包装
//        Type type = returnType.getGenericParameterType();
//        if (type.equals(String.class)) {
//            ObjectMapper objectMapper = new ObjectMapper();
//
//            try {
//                // 将数据包装在ResultVo里后转换为json串进行返回
//                return objectMapper.writeValueAsString(new ResponseVO(body));
//            } catch (JsonProcessingException e) {
//                throw new APIException(ExceptionStatus.ExceptionStatus, e.getMessage());
//            }
//        } else if (type.equals(ResponseVO.class)) {
//            // 由于已经是Response了，就不用再包装，直接返回
//            return body;
//        }

        return new ResponseVO(body);
    }
}
