package com.reaier.engking.service.impl;

import com.reaier.engking.domain.Source;
import com.reaier.engking.domain.User;
import com.reaier.engking.domain.UserWord;
import com.reaier.engking.domain.word.English;
import com.reaier.engking.repository.UserWordsRepository;
import com.reaier.engking.service.UserWordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserWordServiceImpl implements UserWordsService {
    @Autowired
    UserWordsRepository repository;

    @Override
    public UserWord insert(User user, English english, Source source) {
        UserWord word = findByUserIdAndWordId(user.getId(), english.getId());
        if (word == null) {
            word = UserWord.builder()
                    .language(source.getLanguage())
                    .creatAt(new Date())
                    .sourceId(source.getId())
                    .wordId(english.getId())
                    .userId(user.getId())
                    .build();

            repository.save(word);
        }

        return word;
    }

    @Override
    public Page<English> findBySourceId(Integer sourceId, Integer page, Integer size) {
        return repository.findAllBySourceId(sourceId, new PageRequest(page -1, size));
    }

    @Override
    public UserWord findByUserIdAndWordId(Integer userId, Integer wordId) {
        return repository.findFirstByUserIdAndWordId(userId, wordId);
    }
}
