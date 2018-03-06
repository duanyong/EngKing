package com.reaier.engking.service;

import com.reaier.engking.constants.WordProcess;
import com.reaier.engking.domain.Source;
import com.reaier.engking.domain.User;
import com.reaier.engking.domain.word.English;
import org.springframework.data.domain.Page;

public interface SourceService {
    Source getId(Integer id);

    Source insert(Source source);
    Page<Source> findAllByUser(User user, Integer page, Integer size);
    Page<English> findAllBySource(Source source, Integer page, Integer size);


    Source proccess(User user, Source source);

    Source getOneByStatus(WordProcess status);

    void proccessUrl(User user, Source source);
    void proccessImage(User user, Source source);
    void proccessText(User user, Source source);
}
