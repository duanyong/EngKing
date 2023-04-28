package com.reaier.engking.sequence.ocr;

import com.reaier.engking.domain.Source;
import com.reaier.engking.sequence.ocr.exception.OCRException;
import com.reaier.engking.sequence.source.Text;

import java.util.List;

public interface OCRService {
    List<Text> ocr(Source source) throws OCRException;
}
