package com.reaier.engking.sequence.job;

import com.reaier.engking.domain.Source;
import com.reaier.engking.sequence.ocr.OCRService;
import com.reaier.engking.sequence.ocr.exception.OCRException;
import com.reaier.engking.sequence.ocr.tencent.TencentOCR;

public class OCRPipeline extends AbstractPipeline {
    @Override
    void pipeline(Source source) throws RuntimeException {
        OCRService ocrService = new TencentOCR();

        try {
            ocrService.ocr(source);
        } catch (OCRException e) {
            throw e;
        }
    }
}
