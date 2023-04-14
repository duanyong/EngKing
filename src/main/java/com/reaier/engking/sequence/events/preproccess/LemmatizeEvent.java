package com.reaier.engking.sequence.events.preproccess;

import com.reaier.engking.domain.Source;
import lombok.Builder;
import org.springframework.context.ApplicationEvent;

@Builder
public class LemmatizeEvent extends ApplicationEvent {
    public LemmatizeEvent() {
        this(null);
    }

    public LemmatizeEvent(Source source) {
        super(source);
    }
}
