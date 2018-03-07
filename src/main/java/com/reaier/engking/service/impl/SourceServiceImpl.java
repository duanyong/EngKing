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
    public Source getId(Integer id) {
        return sourceRepository.findFirstById(id);
    }

    @Override
    public Source insert(Source source) {
        return sourceRepository.save(source);
    }

    @Override
    public Page<Source> findAllByUser(User user, Integer page, Integer size) {
        return sourceRepository.findAllByUserId(user.getId(), new PageRequest(page -1, size));
    }

    @Override
    public Page<English> findWordsBySource(Source source, Integer page, Integer size) {
        return userWordsService.findBySourceId(source.getId(), page, size);
    }

    @Override
    public Source proccess(User user, Source source) {
        source.setStatus(WordProcess.DOING);
        sourceRepository.save(source);

        switch (source.getType()) {
            case URL: proccessUrl(user, source); break;
            case TEXT: proccessText(user, source); break;
            case IMAGE: proccessImage(user, source); break;
        }

        source.setStatus(WordProcess.DONE);

        return sourceRepository.save(source);
    }

    @Override
    public Source getOneByStatus(WordProcess status) {
        return sourceRepository.findFirstByStatusOrderById(status);
    }

    @Override
    public void proccessUrl(User user, Source source) {

    }

    @Override
    public void proccessImage(User user, Source source) {

    }

    @Override
    public void proccessText(User user, Source source) {
        //按空格分折文本，然后按对应的单词插入到单词表中
        Pattern pattern = Pattern.compile("\\w+");
        Matcher matcher = pattern.matcher(source.getContent());

        English english;
        while(matcher.find()){
            english = englishService.insert(matcher.group().toLowerCase(), source);
            userWordsService.insert(user, english, source);
        }
    }
}
