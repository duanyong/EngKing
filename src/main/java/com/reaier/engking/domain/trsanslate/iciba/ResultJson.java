package com.reaier.engking.domain.trsanslate.iciba;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResultJson {
    @JsonProperty("exchange")
    Tense exchange;

//    @JsonIgnore
    @JsonProperty("symbols")
    Phonetic[] phonetics;

    @JsonProperty("word_name")
    String word;
}
