package com.reaier.engking.tasks;

import com.reaier.engking.constants.WordProcess;
import com.reaier.engking.domain.Source;
import com.reaier.engking.domain.word.EnWord;
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

        List<EnWord> list;
        while (null != ( list = enWordService.getListByStatus(WordProcess.DOING, 1, 100) )) {
            for (EnWord word : list) {
                //NOT DONE
                translateService.translate(word.getWord());
            }
        }



        source.setStatus(WordProcess.DOING);
        sourceService.update(source);

        logger.info("The time is now {}", dateFormat.format(new Date()));
    }


}
