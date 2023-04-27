package com.reaier.engking.sequence.dictionary;

import com.reaier.engking.ApplicationTest;
import com.reaier.engking.domain.Word;
import com.reaier.engking.repository.WordRepository;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class TranslateServiceTest extends ApplicationTest {

    @Resource
    TranslateService translateService;

    @Resource
    WordRepository wordRepository;

    Word word;

    @BeforeEach
    private void before() {
        word = wordRepository.findById(1).orElse(null);
    }

    @Test
    void translate() {
        translateService.translate(word);
    }
}