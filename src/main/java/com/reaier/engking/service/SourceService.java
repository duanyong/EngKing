package com.reaier.engking.service;

import com.reaier.engking.constants.WordProcess;
import com.reaier.engking.domain.Source;
import com.reaier.engking.domain.User;
import org.springframework.data.domain.Page;

public interface SourceService {
    Source insert(Source source);
    Source update(Source source);
    Page<Source> getListByUser(User user, Integer page, Integer size);

    Source proccess(Source source);

    Source getOneByStatus(WordProcess status);

    void proccessUrl(Source source);
    void proccessImage(Source source);
    void proccessText(Source source);
}
