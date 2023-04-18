package com.reaier.engking.sequence.publisher;


import com.reaier.engking.constants.SourceProcess;
import com.reaier.engking.constants.SourceProcessStatus;
import com.reaier.engking.domain.Source;
import com.reaier.engking.sequence.events.preproccess.LemmatizeEvent;
import com.reaier.engking.sequence.events.SourceEvent;
import com.reaier.engking.sequence.events.preproccess.OCREvent;
import com.reaier.engking.sequence.events.preproccess.TransEvent;
import com.reaier.engking.sequence.events.preproccess.URLEvent;
import com.reaier.engking.service.SourceService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class ProcessPublisher extends AbstractProcessPublisher {
    @Resource
    ApplicationEventPublisher publisher;

    @Resource
    SourceService sourceService;

    @EventListener
    public void doEvent(SourceEvent event) {
        Source source = (Source) event.getSource();
        if (SourceProcessStatus.FAIL.equals(source.getProcessStatus())) {
            // todo: 上次的翻译出错了
            return;
        }

        if (SourceProcessStatus.DONE.equals(source.getProcessStatus())) {
            SourceProcess process = next(source);
            if (null == process) {
                process = SourceProcess.FINISH;
            }

            source.setCurrentProcess(process);
            source.setProcessStatus(SourceProcess.FINISH.equals(process) ? SourceProcessStatus.DONE : SourceProcessStatus.DOING);

            sourceService.update(source);

            // 开始处理对应的状态
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

                case TRANSLATION:
                    publisher.publishEvent(new TransEvent(source));

                    break;
            }
        }
    }
}
