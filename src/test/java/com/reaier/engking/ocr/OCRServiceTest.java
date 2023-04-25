package com.reaier.engking.ocr;

import com.reaier.engking.ApplicationTest;
import com.reaier.engking.domain.Source;
import com.reaier.engking.service.SourceService;
import com.reaier.engking.sequence.ocr.OCRService;
import com.reaier.engking.utils.JsonUtils;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;

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
        Source source = Source.builder().content("https://bpic.588ku.com/element_origin_min_pic/19/04/22/179275a55d22dd5d535f2149d80ccc5b.jpg").build();
        ocrService.ocr(source);

        log.info(JsonUtils.obj2Json(source));
    }
}