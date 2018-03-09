package com.reaier.engking.controller.result;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.reaier.engking.controller.result.page.Pagebar;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ListResult<T> extends Response {
    T t;
    Pagebar pagebar;
}
