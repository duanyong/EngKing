package com.reaier.engking.sequence.publisher;


import com.reaier.engking.constants.SourceProcessStatus;
import com.reaier.engking.domain.Source;
import com.reaier.engking.domain.SourceWord;
import com.reaier.engking.domain.Word;
import com.reaier.engking.repository.SourceWordRepository;
import com.reaier.engking.repository.WordRepository;
import com.reaier.engking.sequence.events.preproccess.OCREvent;
import com.reaier.engking.sequence.text.TextService;
import jakarta.annotation.Resource;
import org.springframework.context.event.EventListener;

import java.util.*;

public class LemmaPublisher extends AbstractProcessPublisher {
    @Resource
    WordRepository wordRepository;

    @Resource
    SourceWordRepository sourceWordRepository;

    @Resource
    TextService textService;

    @EventListener
    public void doEvent(OCREvent event) {
        Source source = (Source) event.getSource();

        textService.tokenize(source);

        Word word;
        SourceWord sourceWord;
        for (Word item : source.getLemma()) {
            word = wordRepository.findByName(item.getName());
            if (Objects.isNull(word)) {
                word = wordRepository.save(Word.builder()
                        .name(item.getName())
                        .phonics(null)
                        .translation(null)
                        .build());

                wordRepository.save(word);
            }
        }

        source.setProcessStatus(SourceProcessStatus.DONE);
    }
}