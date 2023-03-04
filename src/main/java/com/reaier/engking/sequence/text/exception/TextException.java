package com.reaier.engking.sequence.text.exception;

import lombok.Getter;

@Getter
public class TextException extends RuntimeException {
    public TextException(Exception e) {
        super(e);
    }
}
