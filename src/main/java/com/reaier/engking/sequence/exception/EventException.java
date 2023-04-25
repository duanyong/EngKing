package com.reaier.engking.sequence.exception;

import com.reaier.engking.constants.SourceProcess;
import lombok.Getter;

@Getter
public class EventException extends RuntimeException {
    SourceProcess process;

    public EventException(SourceProcess process, String message) {
        super(message);

        this.process = process;
    }
}
