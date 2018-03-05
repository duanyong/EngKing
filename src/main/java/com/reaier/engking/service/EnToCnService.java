package com.reaier.engking.service;

import com.reaier.engking.domain.dictionary.en2cn.EnToCn;
import com.reaier.engking.domain.word.English;

public interface EnToCnService {
    EnToCn findByEnWordId(int enWordId);

    EnToCn insert(English word, String part, String means);
}
