package com.reaier.engking.controller.result.source;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.reaier.core.utils.DateUtils;
import com.reaier.engking.constants.WordProcess;
import com.reaier.engking.controller.result.Response;
import lombok.Data;

import java.util.Date;

@Data
public class Source extends Response {
    Integer id;

    WordProcess status;

//    @JsonProperty("paid_time")
    @JsonFormat(pattern = DateUtils.DATAFORMAT)
    Date time;
}
