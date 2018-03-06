package com.reaier.engking.service.impl;

import com.reaier.engking.constants.WordProcess;
import com.reaier.engking.domain.User;
import com.reaier.engking.domain.word.English;
import com.reaier.engking.domain.Source;
import com.reaier.engking.repository.SourceRepository;
import com.reaier.engking.service.EnglishService;
import com.reaier.engking.service.SourceService;
import com.reaier.engking.service.UserWordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class SourceServiceImpl implements SourceService {

    @Autowired
    SourceRepository sourceRepository;

    @Autowired
    EnglishService englishService;

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
    public Page<Source> getListByUser(User user, Integer page, Integer size) {
        return sourceRepository.findAllByUserId(user.getId(), new PageRequest(page -1, size));
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

        English english;
        while(matcher.find()){
            english = englishService.insert(matcher.group().toLowerCase(), source);
            userWordsService.insert(source.getUserId(), english.getId(), source);
        }
    }
}
