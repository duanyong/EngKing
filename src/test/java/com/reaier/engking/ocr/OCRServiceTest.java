package com.reaier.engking.ocr;

import com.reaier.engking.ApplicationTest;
import com.reaier.engking.domain.Source;
import com.reaier.engking.service.SourceService;
import com.reaier.engking.sequence.ocr.OCRService;
import com.reaier.engking.utils.JsonUtils;
import jakarta.annotation.Resource;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@Log4j2
class OCRServiceTest extends ApplicationTest {

    @Resource
    OCRService ocrService;

    @Resource
    SourceService sourceService;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }


    @Test
    void ocr() {
        Source source = Source.builder().source("https://client.sina.com.cn/static/abc.png").content("https://client.sina.com.cn/static/abc.png").build();
        ocrService.ocr(source);

        log.info(JsonUtils.obj2Json(source));
    }
}