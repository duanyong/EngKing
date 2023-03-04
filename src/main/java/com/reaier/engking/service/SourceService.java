package com.reaier.engking.service;

import com.reaier.engking.constants.SourceType;
import com.reaier.engking.domain.Source;
import org.springframework.data.domain.Page;

import java.io.File;

public interface SourceService {
    Source update(Source source);
    Source findById(Long id);

    Source process(Source source);

    boolean processUrl(Source source);
    boolean processImage(Source source);
    boolean processText(Source source);

    Source findByToken(String token);

    Page<Source> findAllByType(SourceType type, int page, int size);
    Page<Source> findAllByPage(int page, int size);
}
