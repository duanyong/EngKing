package com.reaier.engking.service;

import com.reaier.engking.constants.Language;
import com.reaier.engking.domain.dictionary.EnglishDictionary;

import java.util.List;

public interface DictionaryService {
    List<? extends EnglishDictionary> findMeansByWordId(Language language, Integer wordId);
}
