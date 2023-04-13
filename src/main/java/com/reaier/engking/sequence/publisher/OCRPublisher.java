package com.reaier.engking.sequence.publisher;


import com.reaier.engking.constants.SourceType;
import com.reaier.engking.domain.Source;
import com.reaier.engking.sequence.ocr.OCRService;
import com.reaier.engking.sequence.ocr.exception.OCRException;
import com.reaier.engking.service.SourceService;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@Profile("prod")
public class OCRPublisher {
    @Resource
    SourceService sourceService;

    @Resource
    OCRService ocrService;

    public void ocr() {
        // 如果是图片，提取图片中的内容
        Page<Source> sources = sourceService.findAllByType(SourceType.IMAGE, 1, 10);
        for (Source source : sources) {
            try {
                ocrService.ocr(source);

                // todo: 针对图片的处理流程
            } catch (OCRException e) {
                // 识别失败
            }


        }


    }
}
