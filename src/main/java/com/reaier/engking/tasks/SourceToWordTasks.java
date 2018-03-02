package com.reaier.engking.tasks;

import com.reaier.engking.constants.WordProcess;
import com.reaier.engking.domain.Source;
import com.reaier.engking.domain.dictionary.en2cn.EnToCn;
import com.reaier.engking.domain.word.EnWord;
import com.reaier.engking.service.EnToCnService;
import com.reaier.engking.service.EnWordService;
import com.reaier.engking.service.SourceService;
import com.reaier.engking.translate.EnToCnTranslateService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
public class SourceToWordTasks {
    private final org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Autowired
    SourceService sourceService;

    @Autowired
    EnWordService enWordService;

    EnToCnService enToCnService;

    @Autowired
    EnToCnTranslateService translateService;

    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime() {
        Source source = sourceService.getOneByStatus(WordProcess.WAIT);
        if (source == null) {
            return;
        }

        switch (source.getType()) {
            case TEXT:
                sourceService.proccessText(source);
        }

        EnWord english = null;
        List<EnWord> list = enWordService.getListByStatus(WordProcess.WAIT, 1, 100);
        while (list.size() > 0) {
            for (EnWord word : list) {
                EnToCn[] cns = translateService.translate(word.getWord());

                for (EnToCn cn : cns) {
                    if (english == null) {
                        if (null == ( english = enWordService.findWordByName(cn.getWord().getWord()) )) {
                            //需要把英文单词存储起来，按道理不会出来这个错误
                            english = new EnWord();
                            english.setWord(cn.getWord().getWord());
                        }

                        english.setEnPhonetic(cn.getWord().getEnPhonetic());
                        english.setEnMp3(cn.getWord().getEnMp3());
                        english.setAmPhonetic(cn.getWord().getAmPhonetic());
                        english.setAmMp3(cn.getWord().getAmMp3());

                        english = enWordService.update(english);
                    }

                    cn.setEnWordId(english.getId());

                    enToCnService.insert(english, cn.getPart(), cn.getMeans());
                }
            }
        }

        source.setStatus(WordProcess.DOING);
        sourceService.update(source);

        logger.info("The time is now {}", dateFormat.format(new Date()));
    }


}
