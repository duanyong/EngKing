package com.reaier.engking.controller.request.page;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Setter;

@Setter
public class RequestPageVO implements IRequestPageVO {
    @JsonIgnore
    public final static Integer DEFAULT_PAGE = 1;
    @JsonIgnore
    public final static Integer DEFAULT_SIZE = 50;

    @Min(value = 1, message = "页码不能小于1")
    @Max(value = 500, message = "页码不能大于500")
    @JsonProperty("page")
    public Integer page;

    @Min(value = 1, message = "数量不能小于1")
    @Max(value = 500, message = "数量不能大于500")
    @JsonProperty("size")
    public Integer size;

    @Override
    public Integer getPage() {
        return page == null ? DEFAULT_PAGE : page;
    }

    @Override
    public Integer getSize() {
        return size == null ? DEFAULT_SIZE : size;
    }
}
