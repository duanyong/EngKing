package com.reaier.engking.controller.result;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.reaier.core.controller.result.RestResult;
import com.reaier.engking.controller.result.source.SourceRT;
import com.reaier.engking.domain.Source;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SourceResult extends RestResult {
    SourceRT source;
    Page page;


    public SourceResult() {
        this.code       = RestResult.RootSuccessCode;
        this.message    = RestResult.RootSuccessMessage;

        this.source     = new SourceRT();
    }

    public static SourceResult list(Page page) {
        SourceResult result = new SourceResult();

        result.page = page;

        return result;
    }
}
