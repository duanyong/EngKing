package com.reaier.engking.service.impl;


import com.reaier.engking.domain.dictionary.EnglishToChinese;
import com.reaier.engking.domain.dictionary.English;
import com.reaier.engking.repository.EnToCnRepository;
import com.reaier.engking.service.EnToCnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnToCnServiceImpl implements EnToCnService {
    @Autowired
    EnToCnRepository repository;

    @Override
    public EnglishToChinese findByEnWordId(int enWordId) {
        return repository.findOne(enWordId);
    }

    @Override
    public EnglishToChinese insert(English word, String part, String means) {
        EnglishToChinese enToCn = new EnglishToChinese();
        enToCn.setEnglishId(word.getId());
        enToCn.setMeans(means);
        enToCn.setPart(part);

        return repository.save(enToCn);
    }
}
