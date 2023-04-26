package com.reaier.engking.sequence.listener;


import com.reaier.engking.constants.SourceProcess;
import com.reaier.engking.constants.SourceProcessStatus;
import com.reaier.engking.domain.Source;
import com.reaier.engking.sequence.events.SourceEvent;
import com.reaier.engking.sequence.events.preproccess.LemmaEvent;
import com.reaier.engking.sequence.exception.EventException;
import com.reaier.engking.sequence.text.TextService;
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
public class LemmaListener extends AbstractProcessListener {
    @Resource
    TextService textService;

    @Async
    @EventListener
    public void doEvent(LemmaEvent event) {
        Source source = (Source) event.getSource();
        try {
            textService.tokenize(source);
            source.setProcessStatus(SourceProcessStatus.DONE);
        } catch (Exception e) {
            source.setProcessStatus(SourceProcessStatus.FAIL);

            e.printStackTrace();
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
        newest.setLemmas(source.getLemmas());
        newest.setCurrentProcess(SourceProcess.LEMMA);
        newest.setProcessStatus(source.getProcessStatus());

        sourceRepository.save(newest);
    }

    @Recover
    private void Recover(OptimisticLockException exception, Integer id, SourceProcessStatus status) {
        log.warn("Lemma保存时失败，失败的SourceId[{}]，需要保存的状态：{}, Message: {}", id, status, exception.getMessage());

        throw new EventException(SourceProcess.LEMMA, "尝试保存Lemma结果时失败");
    }
}