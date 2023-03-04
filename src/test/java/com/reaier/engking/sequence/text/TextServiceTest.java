package com.reaier.engking.sequence.text;

import com.reaier.engking.ApplicationTest;
import com.reaier.engking.domain.Source;
import com.reaier.engking.service.SourceService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;


class TextServiceTest extends ApplicationTest {
    @Resource
    SourceService sourceService;

    @Resource
    TextService textService;

    Source source;

    @BeforeEach
    void setUp() {
        source = sourceService.findById(2L);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void tokenize() {
        textService.tokenize(source);
    }
}