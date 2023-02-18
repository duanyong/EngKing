package com.reaier.engking.ocr.describe;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Point {
    @JsonProperty("x")
    Long X;

    @JsonProperty("y")
    Long Y;
}
