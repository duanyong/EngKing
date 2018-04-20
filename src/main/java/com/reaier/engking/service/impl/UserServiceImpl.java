package com.reaier.engking.service.impl;

import com.reaier.engking.domain.User;
import com.reaier.engking.repository.UserRepository;
import com.reaier.engking.service.LoginService;
import com.reaier.engking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository repository;

    @Autowired
    LoginService loginService;

    @Override
    public User findById(Integer userId) {
        return loginService.findUserByToken("duanyong");
//        return repository.findById(userId);
    }
}
