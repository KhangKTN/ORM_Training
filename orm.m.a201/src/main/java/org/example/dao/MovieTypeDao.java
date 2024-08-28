package org.example.dao;

import org.example.entities.MovieType;
import org.hibernate.HibernateException;

import java.util.List;

public interface MovieTypeDao {
    boolean saveOrUpdate(final MovieType object) throws HibernateException;
    List<MovieType> findAll() throws HibernateException;
    List<MovieType> findByMovieId(final int movieId) throws HibernateException;
    List<MovieType> findByTypeId(final int typeId) throws HibernateException;
    boolean deleteByTypeId(final int typeId) throws HibernateException;
    MovieType findById(final int typeId, final int movieId) throws HibernateException;
}
