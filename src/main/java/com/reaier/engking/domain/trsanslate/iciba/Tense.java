package com.reaier.engking.domain.trsanslate.iciba;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Tense {
    @JsonIgnore
    @JsonProperty("word_done")
    String[] wordDone;

    @JsonIgnore
    @JsonProperty("word_ing")
    String[] wordIng;

    @JsonIgnore
    @JsonProperty("word_er")
    String[] wordEr;

    @JsonIgnore
    @JsonProperty("word_est")
    String[] wordEst;

    @JsonIgnore
    @JsonProperty("word_past")
    String[] WordPast;

    @JsonIgnore
    @JsonProperty("word_third")
    String[] WordThird;
}
