package org.example.dao;

import org.hibernate.HibernateException;

import java.util.List;

public interface BaseDao<T>{
    boolean saveOrUpdate(final T object) throws HibernateException;
    T getById(final Long id) throws HibernateException;
    boolean deleteById(final int id) throws HibernateException;
    List<T> getAll();
}
