package com.reaier.engking.sequence.publisher;


import com.reaier.engking.constants.SourceProcessStatus;
import com.reaier.engking.domain.Source;
import com.reaier.engking.domain.SourceWord;
import com.reaier.engking.domain.Word;
import com.reaier.engking.repository.SourceWordRepository;
import com.reaier.engking.repository.WordRepository;
import com.reaier.engking.sequence.events.preproccess.OCREvent;
import com.reaier.engking.sequence.ocr.OCRService;
import com.reaier.engking.sequence.ocr.describe.Coordinate;
import com.reaier.engking.sequence.ocr.exception.OCRException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

@Component
@Profile("prod")
public class TranslatePublisher extends AbstractProcessPublisher {
    @Resource
    OCRService ocrService;

    @Resource
    WordRepository wordRepository;

    @Resource
    SourceWordRepository sourceWordRepository;

    @EventListener
    public void doEvent(OCREvent event) {
        // 如果是图片，提取图片中的内容
        Source source = (Source) event.getSource();
        try {
            ocrService.ocr(source);
            source.setProcessStatus(SourceProcessStatus.DONE);

        } catch (OCRException e) {
            // 识别失败
            source.setProcessStatus(SourceProcessStatus.FAIL);
        }

        // 将识别出来的单词存储起来
        List<Coordinate> list = source.getCoordinate();
        for (Coordinate item : list) {
            if (StringUtils.isNotEmpty(item.getWord())) {
                Word word = wordRepository.findByName(item.getWord());
                if (Objects.isNull(word)) {
                    word = Word.builder()
                            .name(item.getWord())
                            .phonics(null)
                            .translation(null)
                            .build();

                    wordRepository.save(word);
                }

                SourceWord sourceWord = sourceWordRepository.findBySourceIdAndWordId(source.getId(), word.getId());
                if (Objects.isNull(sourceWord)) {
                    sourceWord = SourceWord.builder().sourceId(source.getId()).wordId(word.getId()).build();
                    sourceWordRepository.save(sourceWord);
                }
            }
        }

        source.setProcessStatus(SourceProcessStatus.DONE);
        sourceRepository.save(source);
    }
}
