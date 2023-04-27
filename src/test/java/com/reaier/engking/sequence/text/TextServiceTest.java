package com.reaier.engking.sequence.text;

import com.reaier.engking.ApplicationTest;
import com.reaier.engking.domain.Source;
import com.reaier.engking.service.SourceService;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;



class TextServiceTest extends ApplicationTest {
    @Resource
    SourceService sourceService;

    @Resource
    TextService textService;

    Source source;

    @BeforeEach
    void setUp() {
        source = sourceService.findById(2);
    }

    @AfterEach
    void tearDown() {
    }


    void tokenize() {
        textService.tokenize(source);
    }
}