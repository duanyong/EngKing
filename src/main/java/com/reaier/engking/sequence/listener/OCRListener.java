package com.reaier.engking.sequence.listener;


import com.reaier.engking.constants.SourceProcess;
import com.reaier.engking.constants.SourceProcessStatus;
import com.reaier.engking.domain.Source;
import com.reaier.engking.repository.SourceRepository;
import com.reaier.engking.sequence.events.SourceEvent;
import com.reaier.engking.sequence.events.preproccess.OCREvent;
import com.reaier.engking.sequence.exception.EventException;
import com.reaier.engking.sequence.ocr.OCRService;
import com.reaier.engking.sequence.ocr.describe.Coordinate;
import com.reaier.engking.sequence.ocr.exception.OCRException;
import com.reaier.engking.sequence.source.Text;
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

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@Slf4j
@Component
//@Profile("prod")
public class OCRListener extends AbstractProcessListener {
    @Resource
    OCRService ocrService;

    @Resource
    SourceRepository sourceRepository;

    // https://blog.csdn.net/daijiguo/article/details/85078433 关于@Async注解所起子线程会随着主线程退出而退出的问题的分析
    @Async
    @EventListener
    public void doEvent(OCREvent event) throws EventException {
        // 如果是图片，提取图片中的内容
        Source source = (Source) event.getSource();

        log.info("开始处理 OCR 事件: {}", source.getSource());
        List<Text> texts;
        try {
            texts = ocrService.ocr(source);
        } catch (OCRException e) {
            // 识别失败
            log.warn("处理 OCR 失败: {}，错误原因: {}", source.getSource(), e.getMessage());

            source.setProcessStatus(SourceProcessStatus.FAIL);
            update(source.getId(), source);

            throw new EventException(source.getCurrentProcess(), e.getMessage());
        }

        StringBuilder content = new StringBuilder();
        List<Coordinate> words = new LinkedList<>();
        texts.forEach(text -> {
            content.append(text.getText()).append("r\rn");

            text.getWords().forEach(word -> words.add(Coordinate.builder().word(word.getWord()).points(word.getPoints()).build()));
        });


        source.setTexts(content.toString());
        source.setProcessStatus(SourceProcessStatus.DONE);      // OCR 处理成功
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

        newest.setTexts(source.getTexts());
        newest.setCoordinates(source.getCoordinates());
        newest.setCurrentProcess(SourceProcess.OCR);
        newest.setProcessStatus(source.getProcessStatus());

        sourceRepository.save(newest);
    }

    @Recover
    private void Recover(OptimisticLockException exception, Integer id, SourceProcessStatus status) {
        log.warn("OCR保存时失败，失败的SourceId[{}]，需要保存的状态：{}, Message: {}", id, status, exception.getMessage());

        throw new EventException(SourceProcess.OCR, "尝试保存OCR结果时失败");
    }
}
