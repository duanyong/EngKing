package com.reaier.engking.service.impl;


import com.reaier.engking.domain.dictionary.en2cn.EnToCn;
import com.reaier.engking.domain.word.English;
import com.reaier.engking.repository.EnToCnRepository;
import com.reaier.engking.service.EnToCnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnToCnServiceImpl implements EnToCnService {
    @Autowired
    EnToCnRepository repository;

    @Override
    public EnToCn findByEnWordId(int enWordId) {
        return repository.findOne(enWordId);
    }

    @Override
    public EnToCn insert(English word, String part, String means) {
        EnToCn enToCn = new EnToCn();
        enToCn.setEnglishId(word.getId());
        enToCn.setMeans(means);
        enToCn.setPart(part);

        return repository.save(enToCn);
    }
}
