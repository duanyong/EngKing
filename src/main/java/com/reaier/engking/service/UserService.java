package com.reaier.engking.service;

import com.reaier.engking.bean.User;

import java.util.List;


@org.springframework.stereotype.Service
public class UserService extends AbstractService<User> {

    @Override
    public User find(String key) {
        return null;
    }

    @Override
    public User find(Integer key) {
        return null;
    }

    @Override
    public List find(Integer page, Integer size) {
        return null;
    }

    @Override
    public Integer count() {
        return null;
    }

    @Override
    public Boolean delete(Integer id) {
        return null;
    }

    @Override
    public Boolean update(User bean) {
        return null;
    }

    @Override
    public User insert(User bean) {
        return null;
    }
}
