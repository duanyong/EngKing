package com.reaier.engking.sequence.publisher;


import com.reaier.engking.constants.SourceProcess;
import com.reaier.engking.constants.SourceProcessStatus;
import com.reaier.engking.domain.Source;
import com.reaier.engking.sequence.events.preproccess.LemmatizeEvent;
import com.reaier.engking.sequence.events.SourceEvent;
import com.reaier.engking.sequence.events.preproccess.OCREvent;
import com.reaier.engking.sequence.events.preproccess.URLEvent;
import com.reaier.engking.service.SourceService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class PreprocessPublisher {
    @Resource
    ApplicationEventPublisher publisher;

    @Resource
    SourceService sourceService;

    @EventListener
    public void preprocess(SourceEvent event) {
        Source source = (Source) event.getSource();
        SourceProcess process = source.getCurrentProcess();
        if (SourceProcessStatus.SUCCESS.equals(source.getProcessStatus())) {


            if (null == ( process = process.next(source.getProcesses()) )) {
                process = SourceProcess.DONE;
            }
        }

        if (!SourceProcess.DONE.equals(process)) {
            // 将状态置于等待状态开始进行下一步的操作
            source.setProcessStatus(SourceProcessStatus.WAIT);

            sourceService.update(source);

            switch (process) {
                case URL:
                    publisher.publishEvent(new URLEvent(source));
                    break;

                case OCR:
                    publisher.publishEvent(new OCREvent(source));
                    break;

                case TEXT:
                    publisher.publishEvent(new LemmatizeEvent(source));
                    break;
            }
        }
    }
}
