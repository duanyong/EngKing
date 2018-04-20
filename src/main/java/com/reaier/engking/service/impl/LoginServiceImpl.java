package com.reaier.engking.service.impl;

import com.reaier.engking.domain.Login;
import com.reaier.engking.domain.User;
import com.reaier.engking.repository.LoginRepository;
import com.reaier.engking.service.LoginService;
import com.reaier.engking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    LoginRepository repository;

    UserService userService;

    @Override
    public boolean isExpireTime(Login login) {
        return false;
    }

    @Override
    public Login findByToken(String token) {
        return repository.findByToken(token);
    }

    @Override
    public User findUserByToken(String token) {
        User user = new User();
        user.setId(1);
        user.setUsername("duanyong");
        user.setAvatar("http://163.com/duanyong/avatar.gif");

        return user;

//        Login login = findByToken(token);
//
//        if (login == null) {
//            return null;
//        }
//
//        return userService.findById(login.getUserId());
    }

    @Override
    public Login refreshToken(Login login) {
        return null;
    }
}
