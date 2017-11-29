package com.reaier.engking.service;

import com.reaier.engking.bean.AbstractBean;

import java.util.List;

/**
 * Created by PP on 29/11/2017.
 */
public interface IService <T extends AbstractBean> {
    //根据关键字查询单个列表
    T find(String key);

    //根据主键查询单个列表
    T find(Integer key);

    //分页查找单位列表
    List<T> find(Integer page, Integer size);

    //获取总数
    Integer count();

    //插入表数据
    T insert(T bean);

    //更新表数据
    Boolean update(T bean);

    //根据表主键删除
    Boolean delete(Integer id);
}
