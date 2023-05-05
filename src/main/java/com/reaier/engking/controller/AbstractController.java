package com.reaier.engking.controller;


import com.reaier.engking.domain.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public abstract class AbstractController {
    protected final static int DEFAULT_LIMIT_MAX = 50;
    protected final static int DEFAULT_LIMIT_MIN = 1;

    protected User getAuth() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
//            return null;
            return User.builder().id(1).nickname("Tucker Duan").build();
        }

        return ((User) authentication.getCredentials());
    }
//    protected abstract  <T> T domainConvertVO(? extends Integer);
//
//    private Page pagebar(Page page) {
//        return new PageImpl<>(page.getContent().parallelStream().map(item -> domainConvertVO()).collect(Collectors.toList()), page.getPageable(), page.getTotalElements());
//    }
}
