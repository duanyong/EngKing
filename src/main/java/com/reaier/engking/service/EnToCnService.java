package com.reaier.engking.service;

import com.reaier.engking.domain.dictionary.EnglishToChinese;
import com.reaier.engking.domain.dictionary.English;

public interface EnToCnService {
    EnglishToChinese findByEnWordId(int enWordId);

    EnglishToChinese insert(English word, String part, String means);
}
