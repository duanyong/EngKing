package com.reaier.engking.service.impl;


import com.reaier.engking.constants.RecitalPlan;
import com.reaier.engking.domain.Source;
import com.reaier.engking.domain.User;
import com.reaier.engking.domain.UserEnglish;
import com.reaier.engking.service.RecitalListService;
import com.reaier.engking.service.UserWordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecitalListServiceImpl implements RecitalListService {
    @Autowired
    UserWordsService userWordsService;


    @Override
    public boolean generateRecitalPlan(User user, Integer number) {
        return false;
    }

    @Override
    public boolean generateEnglishPlanFromNoPlan(User user, Source source, Integer number) {
        List<UserEnglish> words = userWordsService.findByUserAndSourceAndPlan(user, source, RecitalPlan.NOPLAN,1, number);

        if (words.size() == 0) {
            return false;
        }

        //插入到单词背诵计划中
        for (UserEnglish english : words) {

        }

        return false;
    }
}
