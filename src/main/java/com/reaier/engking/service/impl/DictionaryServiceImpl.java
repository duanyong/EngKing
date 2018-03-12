package com.reaier.engking.service.impl;


import com.reaier.engking.constants.Language;
import com.reaier.engking.domain.dictionary.EnglishDictionary;
import com.reaier.engking.repository.EnToCnRepository;
import com.reaier.engking.service.DictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DictionaryServiceImpl implements DictionaryService {

    @Autowired
    EnToCnRepository enToCnRepository;      //贡汉字典

    @Override
    public List<? extends EnglishDictionary> findMeansByWordId(Language language, Integer wordId) {
        switch (language) {
            case ENGLISH: return enToCnRepository.findAllByEnglishId(wordId);
        }

        return null;
    }
}
