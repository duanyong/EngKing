package com.reaier.engking.advice;

import com.reaier.engking.controller.exception.APIException;
import com.reaier.engking.controller.response.ResponseVO;
import com.reaier.engking.controller.status.BaseApiStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;
import java.util.List;

@Slf4j
@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ControllerExceptionAdvice {

    /**
     * 检验参数
     *
     * */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseVO MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException exception) {
        BindingResult binds = exception.getBindingResult();

        StringBuilder sb = new StringBuilder();
        if (binds.hasErrors()) {
            List<ObjectError> errors = binds.getAllErrors();
            errors.forEach(error -> {
                FieldError field = (FieldError) error;
                log.warn("Bad Request Parameters, Entity {}, field: {}, message: {}", field.getObjectName(), field.getField(), field.getDefaultMessage());

                if (sb.length() != 0) {
                    sb.append("\r\n");
                }

                sb.append(field.getDefaultMessage());
            });
        }

        return new ResponseVO(sb, BaseApiStatus.PARAMETER_VALIDATE_ERROR);
    }

    /**
     * 检验请求方法
     *
     * */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseVO HttpRequestMethodNotSupportedExceptionHandler(HttpRequestMethodNotSupportedException ex) {
        return new ResponseVO(String.format("不支持 '[%s]' 请求，请核对请求类型是否为：'%s'",  ex.getMethod(), Arrays.toString(ex.getSupportedMethods())), BaseApiStatus.METHOD_NOT_SUPPORT_ERROR);
    }

    /**
     * 返回统一的参数
     *
     * */
    @ExceptionHandler({APIException.class})
    public ResponseVO APIExceptionHandler(APIException exception) {
        return new ResponseVO(exception.getMessage(), exception.getStatus());
    }


    /**
     * 统一异常的参数
     *
     * */
    @ExceptionHandler({RuntimeException.class})
    public ResponseVO APIExceptionHandler(RuntimeException exception) {
        return new ResponseVO(exception.getMessage(), BaseApiStatus.FAILED);
    }
}
