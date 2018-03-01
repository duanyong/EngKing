package com.reaier.engking.service.impl;

import com.reaier.engking.constants.WordProcess;
import com.reaier.engking.domain.word.EnWord;
import com.reaier.engking.domain.Source;
import com.reaier.engking.repository.EnWordRepository;
import com.reaier.engking.service.EnWordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnWordServiceImpl implements EnWordService {
    @Autowired
    EnWordRepository enWordRepository;

    @Override
    public EnWord insert(String word, Source source) {
        if (word == null) {
            return null;
        }

        word = word.toLowerCase();
        int hasCode = word.hashCode();

        EnWord enWord = enWordRepository.findFirstByHash(hasCode);
        if (enWord == null) {
            enWord = EnWord.builder()
                    .word(word)
                    .hash(hasCode)
                    .status(WordProcess.WAIT).build();

            enWordRepository.save(enWord);
        }

        return enWord;
    }

    @Override
    public List<EnWord> getListByStatus(WordProcess status, int page, int size) {
        return enWordRepository.getTopByStatus(status, (page -1) * size, size);
    }

}
