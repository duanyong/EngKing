package com.reaier.engking.service;

import com.reaier.engking.constants.PartOfSpeech;
import com.reaier.engking.domain.dictionary.en2cn.EnToCn;
import com.reaier.engking.domain.word.EnWord;

public interface EnToCnService {
    EnToCn findByEnWordId(int enWordId);

    EnToCn insert(EnWord word, PartOfSpeech part, String means);
}
