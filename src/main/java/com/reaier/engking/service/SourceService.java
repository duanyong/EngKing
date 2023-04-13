package com.reaier.engking.service;

import com.reaier.engking.constants.SourceType;
import com.reaier.engking.domain.Source;
import org.springframework.data.domain.Page;

public interface SourceService {
    Source update(Source source);
    Source findById(Long id);

    Source findByToken(String token);

    Page<Source> findAllByType(SourceType type, int page, int size);
    Page<Source> findAllByPage(int page, int size);
}
