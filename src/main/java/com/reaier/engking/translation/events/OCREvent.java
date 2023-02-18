package com.reaier.engking.translation.events;

import com.reaier.engking.domain.Source;
import org.springframework.context.ApplicationEvent;

public class OCREvent extends ApplicationEvent {

    public OCREvent(Source source) {
        super(source);
    }
}
