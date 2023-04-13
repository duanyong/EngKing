package com.reaier.engking.sequence.publisher;


import com.reaier.engking.constants.SourceProcessStatus;
import com.reaier.engking.domain.Source;
import com.reaier.engking.sequence.events.preproccess.OCREvent;
import com.reaier.engking.sequence.ocr.OCRService;
import com.reaier.engking.sequence.ocr.exception.OCRException;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@Profile("prod")
public class OCRPublisher extends AbstractProcessPublisher {
    @Resource
    OCRService ocrService;

    @EventListener
    public void ocrEvent(OCREvent event) {
        // 如果是图片，提取图片中的内容
        Source source = (Source) event.getSource();
        try {
            ocrService.ocr(source);
            source.setProcessStatus(SourceProcessStatus.DONE);

        } catch (OCRException e) {
            // 识别失败
            source.setProcessStatus(SourceProcessStatus.FAIL);
        }

        sourceRepository.save(source);
    }
}
