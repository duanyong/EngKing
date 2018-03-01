package com.reaier.engking.translate;

import com.reaier.engking.domain.dictionary.en2cn.EnToCn;

public interface EnToCnTranslateService {
    EnToCn[] translate(String word);
}
