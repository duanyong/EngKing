package com.reaier.engking.sequence.dictionary.exception;

import lombok.Getter;

@Getter
public class TranslateException extends RuntimeException {
    public TranslateException(Exception e) {
        super(e);
    }
}
