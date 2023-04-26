package com.reaier.engking.sequence.publisher;


import com.reaier.engking.constants.SourceProcess;
import com.reaier.engking.constants.SourceProcessStatus;
import com.reaier.engking.domain.Source;
import com.reaier.engking.sequence.events.preproccess.LemmatizeEvent;
import com.reaier.engking.sequence.events.SourceEvent;
import com.reaier.engking.sequence.events.preproccess.OCREvent;
import com.reaier.engking.sequence.events.preproccess.TransEvent;
import com.reaier.engking.sequence.events.preproccess.URLEvent;
import com.reaier.engking.sequence.exception.EventException;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Slf4j
@Component
public class ProcessPublisher extends AbstractProcessPublisher {
    @Resource
    ApplicationEventPublisher publisher;

    @EventListener
    public void doEvent(SourceEvent event) throws EventException {
        Source source = (Source) event.getSource();
        if (SourceProcessStatus.FAIL.equals(source.getProcessStatus())) {
            // todo: 上次的翻译出错了
            return;
        }


        if (SourceProcessStatus.DONE.equals(source.getProcessStatus())) {
            SourceProcess process;
            SourceProcessStatus status = null;

            process = next(source);
            if (null == process) {
                process = SourceProcess.FINISH;
            }

            try {
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

                status = SourceProcessStatus.DOING;
            } catch (EventException e) {
                // 处理出错
                status  = SourceProcessStatus.FAIL;
                process = e.getProcess();

            } finally {
                source = sourceRepository.findById(source.getId()).orElse(null);
                if (Objects.nonNull(source)) {
                    source.setProcessStatus(status);
                    source.setCurrentProcess(process);
                }
            }
        }
    }
}
