package com.reaier.engking.service.impl;


import com.reaier.engking.constants.PartOfSpeech;
import com.reaier.engking.domain.dictionary.en2cn.EnToCn;
import com.reaier.engking.domain.word.EnWord;
import com.reaier.engking.repository.EnToCnRepository;
import com.reaier.engking.service.EnToCnService;
import org.springframework.stereotype.Service;

@Service
public class EnToCnServiceImpl implements EnToCnService {
    EnToCnRepository repository;

    @Override
    public EnToCn findByEnWordId(int enWordId) {
        return repository.findOne(enWordId);
    }

    @Override
    public EnToCn insert(EnWord word, PartOfSpeech part, String means) {
        EnToCn enToCn = new EnToCn();
        enToCn.setEnWordId(word.getId());
        enToCn.setMeans(means);
        enToCn.setPart(part);

        return repository.save(enToCn);
    }
}
