package com.reaier.engking.dictionary.events;

import com.reaier.engking.domain.Source;
import org.springframework.context.ApplicationEvent;

public class LemmatizeEvent extends ApplicationEvent {

    public LemmatizeEvent(Source source) {
        super(source);
    }
}
