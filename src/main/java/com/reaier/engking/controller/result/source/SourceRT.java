package com.reaier.engking.controller.result.source;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.reaier.core.utils.DateUtils;
import lombok.Data;

import java.util.Date;

@Data
public class SourceRT {
    Integer id;

//    @JsonProperty("paid_time")
    @JsonFormat(pattern = DateUtils.DATAFORMAT)
    Date time;
}
