package com.reaier.engking.ocr.exception;

import lombok.Getter;

@Getter
public class OCRException extends RuntimeException {
    public OCRException(Exception e) {
        super(e);
    }
}
