package com.reaier.engking.sequence.ocr.exception;

import lombok.Getter;

@Getter
public class OCRException extends RuntimeException {
    public OCRException(Exception e) {
        super(e);
    }
}
