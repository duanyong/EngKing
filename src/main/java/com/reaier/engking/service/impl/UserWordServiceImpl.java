package com.reaier.engking.service.impl;

import com.reaier.engking.domain.Source;
import com.reaier.engking.domain.UserWord;
import com.reaier.engking.repository.UserWordsRepository;
import com.reaier.engking.service.UserWordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserWordServiceImpl implements UserWordsService {
    @Autowired
    UserWordsRepository repository;

    @Override
    public UserWord insert(Integer userId, Integer wordId, Source source) {
        UserWord word = findByUserIdAndWordId(userId, wordId);
        if (word == null) {
            word = UserWord.builder()
                    .language(source.getLanguage())
                    .creatAt(new Date())
                    .userId(userId)
                    .wordId(wordId)
                    .build();

            repository.save(word);
        }

        return word;
    }

    @Override
    public UserWord findByUserIdAndWordId(Integer userId, Integer wordId) {
        return repository.findFirstByUserIdAndWordId(userId, wordId);
    }
}
