package com.reaier.engking.service.impl;

import com.reaier.engking.domain.Login;
import com.reaier.engking.repository.LoginRepository;
import com.reaier.engking.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    LoginRepository repository;

    @Override
    public boolean isExpireTime(Login login) {
        return false;
    }

    @Override
    public Login findByToken(String token) {
        return repository.findByToken(token);
    }

    @Override
    public Login refreshToken(Login login) {
        return null;
    }
}
