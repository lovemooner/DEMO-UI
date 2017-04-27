package com.oracle.ca.am.dao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by nadong on 2017/4/24.
 */
public abstract class BaseDao<T>{
    @Autowired
    protected SessionFactory sessionFactory;

    private Class currentClass;

    public BaseDao(Class currentClass) {
        this.currentClass = currentClass;
    }

    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    public void save(T t) {
        getSession().save(t);
    }

    public void delete(T t) {
        getSession().delete(t);
    }

    public void merge(T t) {
        getSession().merge(t);
    }

    public T getById(Long id) {
        return (T) getSession().get(currentClass, id);
    }

    public void update(T t) {
        getSession().update(t);
    }

    public void saveOrUpdate(T t) {
        getSession().saveOrUpdate(t);
    }
}
