package com.reaier.engking.service.impl;

import com.reaier.engking.constants.RecitalPlan;
import com.reaier.engking.domain.Source;
import com.reaier.engking.domain.User;
import com.reaier.engking.domain.UserEnglish;
import com.reaier.engking.domain.dictionary.English;
import com.reaier.engking.repository.UserWordsRepository;
import com.reaier.engking.service.UserWordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserWordServiceImpl implements UserWordsService {
    @Autowired
    UserWordsRepository repository;

    @Override
    public UserEnglish insert(User user, English english, Source source) {
        UserEnglish word = findByUserIdAndWordId(user.getId(), english.getId());
        if (word == null) {
            word = UserEnglish.builder()
                    .language(source.getLanguage())
                    .creatAt(new Date())
                    .plan(RecitalPlan.NOPLAN)
                    .sourceId(source.getId())
                    .wordId(english.getId())
                    .userId(user.getId())
                    .build();

            repository.save(word);
        }

        return word;
    }

    @Override
    public List<UserEnglish> findByUserAndSourceAndPlan(User user, Source source, RecitalPlan plan, Integer page, Integer size) {
        return repository.findAllByUserIdAndSourceIdAndPlan(user.getId(), source.getId(), plan,
                new PageRequest(page -1, size)).getContent();
    }

    @Override
    public UserEnglish findByUserIdAndWordId(Integer userId, Integer wordId) {
        return repository.findFirstByUserIdAndWordId(userId, wordId);
    }
}
