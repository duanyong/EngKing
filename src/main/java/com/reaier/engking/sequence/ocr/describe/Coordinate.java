package com.reaier.engking.sequence.ocr.describe;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class Coordinate {
    @JsonProperty("word")
    String word;

    // 以四个顶点坐标表示，以左上角为起点，顺时针返回
    @JsonProperty("points")
    Point[] points;
}