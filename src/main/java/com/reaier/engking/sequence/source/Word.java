package com.reaier.engking.sequence.source;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Builder
public class Word {
    String word;
    List<Long> points;
}
