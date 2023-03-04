package com.reaier.engking.sequence.text;

import com.reaier.engking.domain.Source;
import com.reaier.engking.sequence.text.exception.TextException;

public interface TextService {
    void tokenize(Source source) throws TextException;

}
