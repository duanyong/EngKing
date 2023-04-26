package com.reaier.engking.sequence.listener;


import com.reaier.engking.repository.SourceRepository;
import jakarta.annotation.Resource;
import org.springframework.context.ApplicationEventPublisher;

public abstract class AbstractProcessListener {
    @Resource
    protected ApplicationEventPublisher publisher;

    @Resource
    protected SourceRepository sourceRepository;
}