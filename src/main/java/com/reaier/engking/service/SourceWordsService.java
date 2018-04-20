package com.reaier.engking.service;

import com.reaier.engking.domain.Source;
import com.reaier.engking.domain.view.SourceWords;
import org.springframework.data.domain.Page;

public interface SourceWordsService {
    Page<SourceWords> findWordsBySource(Source source, Integer page, Integer size);

}
