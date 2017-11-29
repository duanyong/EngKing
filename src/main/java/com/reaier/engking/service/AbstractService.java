package com.reaier.engking.service;

import com.reaier.engking.bean.AbstractBean;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by PP on 10/9/16.
 */

public abstract class AbstractService<T extends AbstractBean> {
    //根据关键字查询单个列表
    public abstract T find(String key);

    //根据主键查询单个列表
    public abstract T find(Integer key);

    //分页查找单位列表
    public abstract List<T> find(Integer page, Integer size);

    //获取总数
    public abstract Integer count();

    //插入表数据
    public abstract T insert(T bean);

    //更新表数据
    public abstract Boolean update(T bean);

    //根据表主键删除
    public abstract Boolean delete(Integer id);


    @Autowired
    private SqlSessionFactory sessionFactory;

    protected SqlSession sqlSession;

    protected <T> getMapper(T t) {
//        if (sqlSession != null) {
//            closeSession();
//        }
//
//        try {
//            sqlSession = sessionFactory.openSession();
//        } catch (Exception execption) {
//            return sqlSession.getMapper(t.getClass());
//        }
    }

    protected void closeSession() {
        if (sessionFactory != null) {
            try {
                sqlSession.close();
            } finally {
                sqlSession = null;
            }
        }
    }
}
