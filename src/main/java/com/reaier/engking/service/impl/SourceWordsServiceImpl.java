package com.reaier.engking.service.impl;


import com.reaier.engking.domain.Source;
import com.reaier.engking.domain.view.SourceWords;
import com.reaier.engking.repository.SourceWordsRepository;
import com.reaier.engking.service.SourceWordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class SourceWordsServiceImpl implements SourceWordsService {
    @Autowired
    SourceWordsRepository repository;

    @Override
    public Page<SourceWords> findWordsBySource(Source source, Integer page, Integer size) {
        return repository.findAllBySourceId(source.getId(), new PageRequest(page -1, size));
    }
}
