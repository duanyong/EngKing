package com.reaier.engking.service;

import com.reaier.engking.domain.Login;

public interface LoginService {
    //检查用户是否过期
    boolean isExpireTime(Login login);

    //通过Token获取登录信息
    Login findByToken(String token);

    //根据token刷新登录信息
    Login refreshToken(Login login);

}
