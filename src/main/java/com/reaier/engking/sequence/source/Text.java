package com.reaier.engking.sequence.source;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class Text {
    String text;
    List<Word> words;
}