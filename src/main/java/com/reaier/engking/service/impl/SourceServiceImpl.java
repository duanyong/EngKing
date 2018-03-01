package com.reaier.engking.service.impl;

import com.reaier.engking.constants.WordProcess;
import com.reaier.engking.domain.word.EnWord;
import com.reaier.engking.domain.Source;
import com.reaier.engking.repository.SourceRepository;
import com.reaier.engking.service.EnWordService;
import com.reaier.engking.service.SourceService;
import com.reaier.engking.service.UserWordsService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class SourceServiceImpl implements SourceService {

    @Autowired
    SourceRepository sourceRepository;

    @Autowired
    EnWordService enWordService;

    @Autowired
    UserWordsService userWordsService;

    @Override
    public Source insert(Source source) {
        return sourceRepository.save(source);
    }

    @Override
    public Source update(Source source) {
        return sourceRepository.save(source);
    }

    @Override
    public Source proccess(Source source) {
        source.setStatus(WordProcess.WAIT);

        switch (source.getType()) {
            case URL:
                proccessUrl(source);
                break;
            case TEXT:
                proccessText(source);
                break;
            case IMAGE:
                proccessImage(source);
                break;

                default:
        }


        return sourceRepository.save(source);
    }

    @Override
    public Source getOneByStatus(WordProcess status) {
        return sourceRepository.findFirstByStatusOrderById(status);
    }

    @Override
    public void proccessUrl(Source source) {
    }

    @Override
    public void proccessImage(Source source) {
    }

    @Override
    public void proccessText(Source source) {
        //按空格分折文本，然后按对应的单词插入到单词表中
        Pattern pattern = Pattern.compile("\\w+");
        Matcher matcher = pattern.matcher(source.getContent());

        EnWord enWord;
        while(matcher.find()){
            enWord = enWordService.insert(matcher.group(), source);
            userWordsService.insert(source.getUserId(), enWord.getId(), source);
        }
    }
}
