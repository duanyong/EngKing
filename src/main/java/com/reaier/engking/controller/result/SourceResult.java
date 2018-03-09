package com.reaier.engking.controller.result;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.reaier.core.controller.result.RestResult;
import com.reaier.engking.controller.result.page.Pagebar;
import lombok.Data;
import org.springframework.data.domain.Page;


@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SourceResult extends RestResult {
    Pagebar pagebar;

    public SourceResult() {
        this.code       = RestResult.RootSuccessCode;
        this.message    = RestResult.RootSuccessMessage;
    }

    public static SourceResult noSource() {
        SourceResult result = new SourceResult();
        result.code     = RestResult.RootErrorCode;
        result.message  = "no source";

        return result;
    }

    public static SourceResult list(Page page) {
        SourceResult result = new SourceResult();

        return result;
    }
}
