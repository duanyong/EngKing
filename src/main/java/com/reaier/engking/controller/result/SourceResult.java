package com.reaier.engking.controller.result;

import com.reaier.core.controller.result.RestResult;
import com.reaier.engking.controller.result.source.SourceRT;
import lombok.Data;

@Data
public class SourceResult extends RestResult {
    SourceRT source;

    public SourceResult() {
        this.code       = RestResult.RootSuccessCode;
        this.message    = RestResult.RootSuccessMessage;

        this.source     = new SourceRT();
    }
}
