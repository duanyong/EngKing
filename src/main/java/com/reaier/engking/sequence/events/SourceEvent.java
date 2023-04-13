package com.reaier.engking.sequence.events;

import com.reaier.engking.domain.Source;
import org.springframework.context.ApplicationEvent;

public class SourceEvent extends ApplicationEvent {

    public SourceEvent(Source source) {
        super(source);
    }
}
