package com.reaier.engking.sequence.publisher;


import com.reaier.engking.constants.SourceProcess;
import com.reaier.engking.constants.SourceProcessStatus;
import com.reaier.engking.domain.Source;
import com.reaier.engking.domain.Word;
import com.reaier.engking.repository.SourceRepository;
import com.reaier.engking.repository.WordRepository;
import com.reaier.engking.sequence.events.SourceEvent;
import com.reaier.engking.sequence.events.preproccess.OCREvent;
import com.reaier.engking.sequence.exception.EventException;
import com.reaier.engking.sequence.ocr.OCRService;
import com.reaier.engking.sequence.ocr.describe.Coordinate;
import com.reaier.engking.sequence.ocr.exception.OCRException;
import com.reaier.engking.utils.WordUtils;
import jakarta.annotation.Resource;
import jakarta.persistence.OptimisticLockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Slf4j
@Component
//@Profile("prod")
public class OCRPublisher extends AbstractProcessPublisher {
    @Resource
    OCRService ocrService;

    @Resource
    WordRepository wordRepository;

    @Resource
    SourceRepository sourceRepository;

    @Async
    @EventListener
    public void doEvent(OCREvent event) throws EventException {
        // 如果是图片，提取图片中的内容
        Source source = (Source) event.getSource();

        log.info("开始处理 OCR 事件: {}", source.getSource());
        try {
            ocrService.ocr(source);
            source.setProcessStatus(SourceProcessStatus.DONE);      // OCR 处理成功
        } catch (OCRException e) {
            // 识别失败
            log.warn("处理 OCR 失败: {}，错误原因: {}", source.getSource(), e.getMessage());

            source.setProcessStatus(SourceProcessStatus.FAIL);
            update(source.getId(), source);

            throw new EventException(source.getCurrentProcess(), e.getMessage());
        }


        update(source.getId(), source);

        // 进行下一步
        publisher.publishEvent(new SourceEvent(source));
    }

    @Retryable(retryFor = OptimisticLockingFailureException.class, maxAttempts = 2, backoff = @Backoff(delay = 200))
    private void update(Integer id, Source source) {
        Source newest = sourceRepository.findById(id).orElse(null);
        if (Objects.isNull(newest)) {
            return ;
        }

        newest.setContent(source.getContent());
        newest.setCoordinate(source.getCoordinate());
        newest.setCurrentProcess(SourceProcess.OCR);
        newest.setProcessStatus(source.getProcessStatus());

        sourceRepository.save(newest);
    }

    @Recover
    private void Recover(OptimisticLockException exception, Integer id, SourceProcessStatus status) {
        log.warn("OCR保存时失败，失败的SourceId[{}]，需要保存的状态：{}, Message: {}", id, status, exception.getMessage());

        throw new EventException(SourceProcess.OCR, "OCR保存时失败");
    }
}
