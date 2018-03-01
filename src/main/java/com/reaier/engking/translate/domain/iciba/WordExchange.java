package com.reaier.engking.translate.domain.iciba;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WordExchange {
    @JsonProperty("word_done")
    String[] wordDone;

    @JsonProperty("word_ing")
    String[] wordIng;

    @JsonProperty("word_er")
    String[] wordEr;

    @JsonProperty("word_est")
    String[] wordEst;

    @JsonProperty("word_past")
    String[] WordPast;

    @JsonProperty("word_third")
    String[] WordThird;
}
