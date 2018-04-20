package com.reaier.engking.tasks;

import com.reaier.engking.constants.WordProcess;
import com.reaier.engking.domain.dictionary.English;
import com.reaier.engking.domain.trsanslate.word.Mean;
import com.reaier.engking.domain.trsanslate.word.Word;
import com.reaier.engking.service.EnToCnService;
import com.reaier.engking.service.EnglishService;
import com.reaier.engking.translate.EnToCnTranslateService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class TranslateWordTasks {
    @Autowired
    EnglishService englishService;

    @Autowired
    EnToCnService enToCnService;

    @Autowired
    EnToCnTranslateService translateService;

    @Scheduled(fixedRate = 5000)
    public void translateWords() {
        Word word;
        List<English> list = englishService.getListByStatus(WordProcess.WAIT, 1, 100);
        for (English english : list) {
            try {
                word = translateService.translate(english.getWord());
            } catch (Exception e) {
                continue;
            }

            if (StringUtils.isEmpty(english.getAmMp3())) {
                english.setAmMp3(word.getPhonetic().getAmMp3());
            }

            if (StringUtils.isEmpty(english.getEnMp3())) {
                english.setEnMp3(word.getPhonetic().getEnMp3());
            }

            if (StringUtils.isEmpty(english.getAmPhonetic())) {
                english.setAmPhonetic(word.getPhonetic().getAmPhonetic());
            }

            if (StringUtils.isEmpty(english.getEnPhonetic())) {
                english.setEnPhonetic(word.getPhonetic().getEnPhonetic());
            }

            List<Mean> means = word.getMeans();
            for (Mean mean : means) {
                enToCnService.insert(english, mean.getPart(), mean.getMeans());
            }

            english.setStatus(WordProcess.DONE);

            englishService.update(english);
        }
    }
}
