package com.reaier.engking.sequence.events.preproccess;

import com.reaier.engking.domain.Source;
import lombok.Builder;
import org.springframework.context.ApplicationEvent;

public class LemmaEvent extends ApplicationEvent {
    public LemmaEvent(Source source) {
        super(source);
    }
}
