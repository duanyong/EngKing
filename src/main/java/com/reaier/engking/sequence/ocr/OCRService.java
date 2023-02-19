package com.reaier.engking.sequence.ocr;

import com.reaier.engking.domain.Source;
import com.reaier.engking.sequence.ocr.exception.OCRException;

public interface OCRService {
    void ocr(Source source) throws OCRException;
}
