package com.reaier.engking.service.impl;

import com.reaier.engking.constants.WordProcess;
import com.reaier.engking.domain.dictionary.English;
import com.reaier.engking.domain.Source;
import com.reaier.engking.repository.EnglishRepository;
import com.reaier.engking.service.EnglishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class EnglishServiceImpl implements EnglishService {
    @Autowired
    EnglishRepository englishRepository;

    @Override
    public int getHashCode(String word) {
        return word.toLowerCase().hashCode();
    }

    @Override
    public English findWordByName(String word) {
        return englishRepository.findFirstByHash(getHashCode(word));
    }

    @Override
    public English insert(String word, Source source) {
        if (word == null) {
            return null;
        }

        English english = findWordByName(word);
        if (english == null) {
            english = English.builder()
                    .word(word)
                    .hash(getHashCode(word))
                    .time(new Date())
                    .status(WordProcess.WAIT).build();

            englishRepository.save(english);
        }

        return english;
    }

    @Override
    public English update(English word) {
        return englishRepository.save(word);
    }

    @Override
    public List<English> getListByStatus(WordProcess status, int page, int size) {
        return englishRepository.findAllByStatus(status, new PageRequest(page -1, size));
    }
}
