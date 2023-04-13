package com.reaier.engking.sequence.events.preproccess;

import com.reaier.engking.domain.Source;
import org.springframework.context.ApplicationEvent;

public class TransEvent extends ApplicationEvent {

    public TransEvent(Source source) {
        super(source);
    }
}