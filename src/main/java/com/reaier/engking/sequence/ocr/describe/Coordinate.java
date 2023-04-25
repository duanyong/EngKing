package com.reaier.engking.sequence.ocr.describe;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Coordinate {
    @JsonProperty("word")
    String word;

    // 是否在单词表中
    @JsonProperty("hit")
    Integer hit;

    // 以四个顶点坐标表示，以左上角为起点，顺时针返回[上左，上右，下右，下左]
    @JsonProperty("points")
    List<Long> points;
}