package com.reaier.engking.sequence.dictionary.baidu.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ICIBAResponse {
    @JsonProperty("error_code")
    String code;

    @JsonProperty("error_msg")
    String message;
}
