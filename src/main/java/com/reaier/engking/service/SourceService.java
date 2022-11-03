package com.reaier.engking.service;

import com.reaier.engking.domain.Source;
import org.springframework.data.domain.Page;

import java.io.File;

public interface SourceService {
    Source update(Source source);
    Source findById(Integer id);

    Source process(Source source);

    boolean processUrl(String uri);
    boolean processImage(File path);
    boolean processText(String text);

    Source findByToken(String token);

    Page<Source> findAllByPage(int page, int size);
}
