package com.reaier.engking.sequence.dictionary;

import com.reaier.engking.domain.Word;
import com.reaier.engking.sequence.dictionary.exception.TranslateException;

public interface TranslateService {
    void translate(Word word) throws TranslateException;
}
