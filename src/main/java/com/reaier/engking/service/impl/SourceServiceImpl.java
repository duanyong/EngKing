package com.reaier.engking.service.impl;

import com.reaier.engking.constants.SourceProcess;
import com.reaier.engking.domain.Source;
import com.reaier.engking.repository.SourceRepository;
import com.reaier.engking.service.SourceService;
import com.reaier.engking.service.EnToCnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class SourceServiceImpl implements SourceService {

    @Autowired
    SourceRepository sourceRepository;

    @Autowired
    EnToCnService wordService;

    @Override
    public Source insert(Source source) {
        source.createByOpenId(source.getUserId().toString());

        return sourceRepository.save(source);
    }

    @Override
    public Source proccess(Source source) {
        switch (source.getType()) {
            case URL:
                proccessUrl(source.getContent());
                break;
            case TEXT:
                proccessText(source.getContent());
                break;
            case IMAGE:
                proccessImage(new File(source.getContent()));
                break;

                default:
        }

        source.setProccessStatus(SourceProcess.DOING);

        return sourceRepository.save(source);
    }

    @Override
    public boolean proccessUrl(String uri) {
        return false;
    }

    @Override
    public boolean proccessImage(File path) {
        return false;
    }

    @Override
    public boolean proccessText(String text) {
        return false;
    }
}
