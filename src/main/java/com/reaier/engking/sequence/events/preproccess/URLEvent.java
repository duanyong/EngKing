package com.reaier.engking.sequence.events.preproccess;

import com.reaier.engking.domain.Source;
import org.springframework.context.ApplicationEvent;

public class URLEvent extends ApplicationEvent {

    public URLEvent(Source source) {
        super(source);
    }
}
