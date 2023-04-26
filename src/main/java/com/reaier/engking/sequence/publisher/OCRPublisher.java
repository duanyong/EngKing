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
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;

import java.util.List;
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

    @EventListener
    public void doEvent(OCREvent event) throws EventException {
        // 如果是图片，提取图片中的内容
        Source source = (Source) event.getSource();

        log.info("开始处理 OCR 事件: {}", source.getSource());
        try {
            ocrService.ocr(source);
            source.setProcessStatus(SourceProcessStatus.DONE);

        } catch (OCRException e) {
            // 识别失败
            source.setProcessStatus(SourceProcessStatus.FAIL);
        }

        // 将识别出来的单词存储起来
        source.getCoordinate().parallelStream().filter(item -> WordUtils.isWord(item.getWord())).forEach(item -> {
            Word word = wordRepository.findByName(item.getWord());

            if (Objects.nonNull(word)) {
                item.setHit(word.getId());
            }
        });


        update(source.getId(), SourceProcessStatus.DONE, source.getCoordinate(), source.getContent());

        // 进行下一步
        publisher.publishEvent(new SourceEvent(source));
    }

    @Retryable(retryFor = OptimisticLockException.class, maxAttempts = 2, backoff = @Backoff(delay = 200))
    private void update(Integer id, SourceProcessStatus status, List<Coordinate> coordinate, String content) {
        Source source = sourceRepository.findById(id).orElse(null);
        if (Objects.isNull(source)) {
            return ;
        }

        source.setContent(content);
        source.setCoordinate(coordinate);
        source.setProcessStatus(status);

        sourceRepository.save(source);
    }

    @Recover
    private void Recover(OptimisticLockException exception, Integer id, SourceProcessStatus status) {
        log.warn("OCR保存时失败，失败的SourceId[{}]，需要保存的状态：{}, Message: {}", id, status, exception.getMessage());

        throw new EventException(SourceProcess.OCR, "OCR保存时失败");
    }
}
