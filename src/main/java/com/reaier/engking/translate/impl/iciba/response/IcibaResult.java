package com.reaier.engking.translate.impl.iciba.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.reaier.engking.translate.domain.iciba.WordExchange;
import com.reaier.engking.translate.domain.iciba.WordDesc;
import lombok.Data;

@Data
public class IcibaResult {
    @JsonProperty("exchange")
    WordExchange exchange;

    @JsonProperty("symbols")
    WordDesc[] descs;
}
