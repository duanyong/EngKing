package com.reaier.engking.domain.trsanslate.word;

import lombok.Data;

import java.util.List;

@Data
public class Word {
    String spell;
    Phonetic phonetic;
    List<Mean> means;
}
