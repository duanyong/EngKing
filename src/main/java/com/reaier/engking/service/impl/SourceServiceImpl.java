package com.reaier.engking.service.impl;

import com.reaier.engking.constants.SourceProcess;
import com.reaier.engking.domain.Source;
import com.reaier.engking.repository.SourceRepository;
import com.reaier.engking.service.SourceService;
import com.reaier.engking.service.EnToCnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class SourceServiceImpl implements SourceService {
    @Autowired
    SourceRepository sourceRepository;

    @Override
    public Source update(Source source) {
        return sourceRepository.save(source);
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

        return sourceRepository.save(source);
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
}
