package com.reaier.engking.sequence.publisher;


import com.reaier.engking.constants.SourceProcess;
import com.reaier.engking.domain.Source;
import com.reaier.engking.repository.SourceRepository;
import org.springframework.context.ApplicationEventPublisher;

import javax.annotation.Resource;

public abstract class AbstractProcessPublisher {
    @Resource
    protected ApplicationEventPublisher publisher;

    @Resource
    protected SourceRepository sourceRepository;

    protected SourceProcess next(Source source) {
        return source.getCurrentProcess().next(source.getProcesses());
    }
}