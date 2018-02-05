package com.reaier.engking.service.impl;

import com.reaier.engking.constants.WordProcess;
import com.reaier.engking.domain.word.EnWord;
import com.reaier.engking.domain.Source;
import com.reaier.engking.repository.EnWordRepository;
import com.reaier.engking.service.EnWordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnWordServiceImpl implements EnWordService {
    @Autowired
    EnWordRepository enWordRepository;

    @Override
    public EnWord insert(String word, Source source) {
        EnWord enWord = enWordRepository.findFirstByHash(word.hashCode());
        if (enWord == null) {
            enWord = EnWord.builder()
                    .sourceId(source.getId())
                    .word(word)
                    .status(WordProcess.WAIT).build();

            enWordRepository.save(enWord);
        }

        return enWord;
    }
}
