package com.reaier.engking.service.impl;

import com.reaier.engking.bean.User;
import com.reaier.engking.dao.service.EntityServiceImpl;
import com.reaier.engking.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends EntityServiceImpl<User> implements UserService {
}
