package com.reaier.engking.translate.impl.iciba.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.reaier.engking.translate.domain.iciba.WordExchange;
import com.reaier.engking.translate.domain.iciba.WordDesc;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class IcibaResult {
    @JsonProperty("exchange")
    WordExchange exchange;

    @JsonProperty("symbols")
    WordDesc[] descs;

    @JsonProperty("word_name")
    String word;
}
