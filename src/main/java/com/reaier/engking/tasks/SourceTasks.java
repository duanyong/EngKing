package com.reaier.engking.tasks;

import com.reaier.engking.constants.WordProcess;
import com.reaier.engking.domain.Source;
import com.reaier.engking.domain.User;
import com.reaier.engking.domain.dictionary.en2cn.EnToCn;
import com.reaier.engking.domain.trsanslate.word.Mean;
import com.reaier.engking.domain.trsanslate.word.Word;
import com.reaier.engking.domain.word.English;
import com.reaier.engking.service.EnToCnService;
import com.reaier.engking.service.EnglishService;
import com.reaier.engking.service.SourceService;
import com.reaier.engking.service.UserService;
import com.reaier.engking.translate.EnToCnTranslateService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
public class SourceTasks {
    private final org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    @Autowired
    UserService userService;

    @Autowired
    SourceService sourceService;


    @Scheduled(fixedRate = 5000)
    public void proccess() {
        Source source;
        if (( source = sourceService.getOneByStatus(WordProcess.WAIT) ) == null) {
            return;
        }

        sourceService.proccess(userService.findById(source.getUserId()), source);
    }
}
