package com.reaier.engking.service.impl;

import com.reaier.engking.constants.SourceType;
import com.reaier.engking.domain.Source;
import com.reaier.engking.repository.SourceRepository;
import com.reaier.engking.repository.WordRepository;
import com.reaier.engking.sequence.ocr.OCRService;
import com.reaier.engking.sequence.text.TextService;
import com.reaier.engking.service.SourceService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SourceServiceImpl implements SourceService {
    @Resource
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
