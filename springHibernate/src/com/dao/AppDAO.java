package com.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class AppDAO {
    @Autowired
    SessionFactory sessionFactory;
    protected Session getSession(){
        Session session = sessionFactory.getCurrentSession();
        return session;
    }
    protected void persist(Object entity){
        getSession().persist(entity);
    }

    protected void delete(Object entity){
        getSession().delete(entity);
    }
    protected void update(Object entity){
        getSession().update(entity);
    }
    protected void merge(Object entity) {getSession().merge(entity);}
}
