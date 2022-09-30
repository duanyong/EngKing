package com.reaier.engking.controller.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.reaier.engking.controller.response.page.Pagebar;
import com.reaier.engking.controller.status.ApiStatus;
import com.reaier.engking.controller.status.BaseApiStatus;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
public class ResponseVO {
    private final int code;
    private final String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object data;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List list;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Pagebar pagebar;

    public ResponseVO(final Object data) {
        this(data, BaseApiStatus.SUCCESS);
    }

    public ResponseVO(final Object data, final ApiStatus status) {
        if (data instanceof List) {
            this.list = (List) data;
        } if (data instanceof Page) {
            Page page = (Page) data;

            this.list = page.getContent();
            this.pagebar = Pagebar.fromPageable(page);
        } else {
            this.data = data;
        }

        this.code = status.getCode();
        this.message = status.getMessage();
    }
}
