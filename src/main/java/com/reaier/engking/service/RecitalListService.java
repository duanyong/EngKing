package com.reaier.engking.service;

import com.reaier.engking.domain.Source;
import com.reaier.engking.domain.User;

//背诵单词列表
public interface RecitalListService {
    //每生成用户的背诵计划
    boolean generateRecitalPlan(User user, Integer number);

    //从用户的新单词表中生成背诵计划
    boolean generateEnglishPlanFromNoPlan(User user, Source sorce, Integer number);


}
