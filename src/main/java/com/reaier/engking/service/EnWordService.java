package com.reaier.engking.service;

import com.reaier.engking.domain.word.EnWord;
import com.reaier.engking.domain.Source;

public interface EnWordService {
    EnWord insert(String word, Source source);
}
