package com.reaier.engking.service.impl;

import com.reaier.engking.constants.SourceProcess;
import com.reaier.engking.constants.SourceType;
import com.reaier.engking.domain.Source;
import com.reaier.engking.repository.SourceRepository;
import com.reaier.engking.service.SourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class SourceServiceImpl implements SourceService {
    @Autowired
    SourceRepository repository;

    @Override
    public Source update(Source source) {
        return repository.save(source);
    }

    @Override
    public Source findById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Source process(Source source) {
        switch (source.getType()) {
            case URL:
                processUrl(source.getContent());
                break;
            case TEXT:
                processText(source.getContent());
                break;
            case IMAGE:
                processImage(new File(source.getContent()));
                break;

                default:
        }

        source.setProcessStatus(SourceProcess.DOING);

        return repository.save(source);
    }

    @Async
    @Override
    public boolean processUrl(String uri) {
        return false;
    }

    @Async
    @Override
    public boolean processImage(File path) {
        return false;
    }

    @Async
    @Override
    public boolean processText(String text) {
        return false;
    }

    @Override
    public Source findByToken(String token) {
        return repository.findByToken(token);
    }

    @Override
    public Page<Source> findAllByType(SourceType type, int page, int size) {
        return repository.findAllByType(type, PageRequest.of(page -1, size));
    }

    @Override
    public Page<Source> findAllByPage(int page, int size) {
        return repository.findAll(PageRequest.of(page -1, size, Sort.by(Sort.Direction.DESC, "id")));
    }
}
