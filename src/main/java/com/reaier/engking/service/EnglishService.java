package com.reaier.engking.service;

import com.reaier.engking.constants.WordProcess;
import com.reaier.engking.domain.word.English;
import com.reaier.engking.domain.Source;

import java.util.List;

public interface EnglishService {
    int getHashCode(String word);
    English findWordByName(String word);

    English insert(String word, Source source);
    English update(English word);
    public List<English> getListByStatus(WordProcess status, int page, int size);
}
