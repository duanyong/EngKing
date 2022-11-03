package com.reaier.engking.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.stream.Collectors;

public abstract class AbstractController {
    protected final static int DEFAULT_LIMIT_MAX = 50;
    protected final static int DEFAULT_LIMIT_MIN = 1;

//    protected abstract  <T> T domainConvertVO(? extends Integer);
//
//    private Page pagebar(Page page) {
//        return new PageImpl<>(page.getContent().parallelStream().map(item -> domainConvertVO()).collect(Collectors.toList()), page.getPageable(), page.getTotalElements());
//    }
}
