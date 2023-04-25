package com.reaier.engking.sequence.publisher;

import com.reaier.engking.ApplicationTest;
import com.reaier.engking.domain.Source;
import com.reaier.engking.repository.SourceRepository;
import com.reaier.engking.repository.SourceWordRepository;
import com.reaier.engking.sequence.events.SourceEvent;
import com.reaier.engking.sequence.events.preproccess.OCREvent;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationEventPublisher;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

class ProcessPublisherTest extends ApplicationTest {
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
    }
}