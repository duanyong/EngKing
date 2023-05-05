package com.reaier.engking.controller.request;

import com.reaier.engking.domain.User;
import lombok.Getter;

@Getter
public class Auth {
    User auth;

    public User getAuth() {
        return User.builder().id(1).nickname("Duan Yong").build();
    }
}
