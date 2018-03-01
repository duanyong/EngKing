package com.reaier.engking.service;

import com.reaier.engking.domain.Source;
import com.reaier.engking.domain.UserWord;

public interface UserWordsService {
    UserWord findByUserIdAndWordId(Integer userId, Integer wordId);
    UserWord insert(Integer userId, Integer wordId, Source source);

}
