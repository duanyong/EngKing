package com.reaier.engking.translation.publisher;


import com.reaier.engking.constants.SourceType;
import com.reaier.engking.domain.Source;
import com.reaier.engking.service.SourceService;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@Profile("prod")
public class OCRPublisher {
    @Resource
    SourceService sourceService;

    @Scheduled(cron = "0 */5 * * * *")
    public void ocr() {
        // 如果是图片，提取图片中的内容
        Page<Source> sources = sourceService.findAllByType(SourceType.IMAGE, 1, 50);



    }
}
