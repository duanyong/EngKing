package com.reaier.engking.task;

import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Calendar;


@Log4j2
@Component
@Profile("prod")
public class ExecutePlanTask {
    private final static Integer StartPage = 1;

    @Scheduled(cron = "58 */5 * * * *")
    protected void executePlan() {
        log.info("每【五】分钟执行补量计划");

        Calendar calendar = Calendar.getInstance();


        log.info("线束每【五】分钟执行补量计划");
    }
}