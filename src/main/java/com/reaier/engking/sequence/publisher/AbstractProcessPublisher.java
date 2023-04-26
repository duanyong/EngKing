package com.reaier.engking.sequence.publisher;


import com.reaier.engking.constants.SourceProcess;
import com.reaier.engking.domain.Source;
import com.reaier.engking.repository.SourceRepository;
import jakarta.annotation.Resource;
import org.springframework.context.ApplicationEventPublisher;

public abstract class AbstractProcessPublisher {
    @Resource
    protected ApplicationEventPublisher publisher;

    @Resource
    protected SourceRepository sourceRepository;

    protected SourceProcess next(Source source) {
        return source.getCurrentProcess().next(source.getProcesses());
    }
}