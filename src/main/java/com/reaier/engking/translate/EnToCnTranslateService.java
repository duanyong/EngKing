package com.reaier.engking.translate;

import com.reaier.engking.domain.trsanslate.word.Word;

public interface EnToCnTranslateService {
    Word translate(String spell);
}
