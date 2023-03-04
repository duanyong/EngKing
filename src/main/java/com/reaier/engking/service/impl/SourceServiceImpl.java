package com.reaier.engking.service.impl;

import com.reaier.engking.constants.SourceProcess;
import com.reaier.engking.constants.SourceType;
import com.reaier.engking.domain.Source;
import com.reaier.engking.domain.Word;
import com.reaier.engking.repository.SourceRepository;
import com.reaier.engking.repository.WordRepository;
import com.reaier.engking.sequence.ocr.OCRService;
import com.reaier.engking.sequence.ocr.exception.OCRException;
import com.reaier.engking.sequence.text.TextService;
import com.reaier.engking.sequence.text.exception.TextException;
import com.reaier.engking.service.SourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

@Service
public class SourceServiceImpl implements SourceService {
    @Autowired
    SourceRepository repository;

    @Autowired
    WordRepository wordRepository;

    @Resource
    OCRService ocrService;

    @Resource
    TextService textService;

    @Override
    public Source update(Source source) {
        return repository.save(source);
    }

    @Override
    public Source findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Source process(Source source) {
        switch (source.getProcessStatus()) {
            case URL:
                processUrl(source);
                break;
            case TEXT:
                processText(source);
                break;
            case OCR:
                processImage(source);
                break;
            case TRANSLATION:

                default:
        }

        return repository.save(source);
    }

    @Async
    @Override
    public boolean processUrl(Source source) {
        return false;
    }

    @Async
    @Override
    public boolean processImage(Source source) {
        boolean success = true;
        try {
            ocrService.ocr(source);
            source.setProcessStatus(SourceProcess.TEXT);
        } catch (OCRException ocrException) {
            success = false;
        }

        return success;
    }

    @Async
    @Override
    public boolean processText(Source source) {
        boolean success = true;
        try {
            textService.tokenize(source);
            source.getLemma().parallelStream()
                    .filter(word -> Objects.isNull(word.getId()))
                    .forEach(item -> wordRepository.save(item));

            source.setProcessStatus(SourceProcess.TRANSLATION);
        } catch (TextException textException) {
            success = false;
        }

        return success;
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
