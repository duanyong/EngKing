package com.reaier.engking.ocr;

import com.reaier.engking.domain.Source;
import com.reaier.engking.ocr.exception.OCRException;

public interface OCRService {
    void ocr(Source source) throws OCRException;
}
