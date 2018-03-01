package com.reaier.engking.tasks;

import com.reaier.engking.constants.WordProcess;
import com.reaier.engking.domain.Source;
import com.reaier.engking.service.SourceService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class SourceToWordTasks {
    private final org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Autowired
    SourceService sourceService;


    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime() {
        Source source = sourceService.getOneByStatus(WordProcess.WAIT);
        if (source == null) {
            return;
        }


        source.setStatus(WordProcess.DOING);
        sourceService.update(source);

        switch (source.getType()) {
            case TEXT:
                sourceService.proccessText(source);
        }

        logger.info("The time is now {}", dateFormat.format(new Date()));
    }


}
