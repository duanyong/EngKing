package com.reaier.engking.sequence.listener;

import com.reaier.engking.ApplicationTest;
import com.reaier.engking.domain.Source;
import com.reaier.engking.repository.SourceRepository;
import com.reaier.engking.sequence.events.SourceEvent;
import com.reaier.engking.sequence.events.preproccess.LemmaEvent;
import com.reaier.engking.sequence.events.preproccess.OCREvent;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationEventPublisher;



class SourcePublisherTest extends ApplicationTest {
    @Resource
    ApplicationEventPublisher publisher;

    @Resource
    SourceRepository sourceRepository;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void doEvent() {
        Source source = sourceRepository.findById(3).orElse(null);

        publisher.publishEvent(new SourceEvent(source));
    }

    @Test
    void doOCREvent() {
        Source source = sourceRepository.findById(3).orElse(null);

        publisher.publishEvent(new OCREvent(source));

        try {
            Thread.sleep(50 * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void doLemmaEvent() {
        Source source = sourceRepository.findById(3).orElse(null);

        publisher.publishEvent(new LemmaEvent(source));

        try {
            Thread.sleep(50 * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}