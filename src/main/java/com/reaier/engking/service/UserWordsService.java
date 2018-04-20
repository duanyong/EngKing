package com.reaier.engking.service;

import com.reaier.engking.constants.RecitalPlan;
import com.reaier.engking.domain.Source;
import com.reaier.engking.domain.User;
import com.reaier.engking.domain.UserEnglish;
import com.reaier.engking.domain.dictionary.English;

import java.util.List;

public interface UserWordsService {
    UserEnglish findByUserIdAndWordId(Integer userId, Integer wordId);
    UserEnglish insert(User user, English english, Source source);

    List<UserEnglish> findByUserAndSourceAndPlan(User user, Source source, RecitalPlan plan, Integer page, Integer size);
}
