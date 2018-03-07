package com.reaier.engking.service;

import com.reaier.engking.domain.Source;
import com.reaier.engking.domain.User;
import com.reaier.engking.domain.UserWord;
import com.reaier.engking.domain.word.English;
import org.springframework.data.domain.Page;

public interface UserWordsService {
    UserWord findByUserIdAndWordId(Integer userId, Integer wordId);
    UserWord insert(User user, English english, Source source);

    Page<UserWord> findBySourceId(Integer sourceId, Integer page, Integer size);

}
