package com.reaier.engking.service.impl;

import com.reaier.engking.constants.WordProcess;
import com.reaier.engking.domain.word.EnWord;
import com.reaier.engking.domain.Source;
import com.reaier.engking.repository.EnWordRepository;
import com.reaier.engking.service.EnWordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnWordServiceImpl implements EnWordService {
    @Autowired
    EnWordRepository enWordRepository;

    @Override
    public int getHashCode(String word) {
        return word.toLowerCase().hashCode();
    }

    @Override
    public EnWord findWordByName(String word) {
        return enWordRepository.findFirstByHash(getHashCode(word));
    }

    @Override
    public EnWord insert(String word, Source source) {
        if (word == null) {
            return null;
        }

        EnWord enWord = findWordByName(word);
        if (enWord == null) {
            enWord = EnWord.builder()
                    .word(word)
                    .hash(getHashCode(word))
                    .status(WordProcess.WAIT).build();

            enWordRepository.save(enWord);
        }

        return enWord;
    }

    @Override
    public EnWord update(EnWord word) {
        return enWordRepository.save(word);
    }

    @Override
    public List<EnWord> getListByStatus(WordProcess status, int page, int size) {
        return enWordRepository.findAllByStatus(status, new PageRequest(page -1, size));
    }

}
