package org.example.dao;

import org.hibernate.HibernateException;

public interface BaseDao <T>{
    boolean saveOrUpdate(final T object) throws HibernateException;
    T getById(final Long id) throws HibernateException;
    boolean deleteById(final int id) throws HibernateException;
}
