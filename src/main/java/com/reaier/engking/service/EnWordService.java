package com.reaier.engking.service;

import com.reaier.engking.constants.WordProcess;
import com.reaier.engking.domain.word.EnWord;
import com.reaier.engking.domain.Source;

import java.util.List;

public interface EnWordService {
    int getHashCode(String word);
    EnWord findWordByName(String word);

    EnWord insert(String word, Source source);
    EnWord update(EnWord word);
    public List<EnWord> getListByStatus(WordProcess status, int page, int size);
}
