package org.example.dao.impl;

import org.example.HibernateUtils;
import org.example.dao.MovieTypeDao;
import org.example.entities.MovieType;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class MovieTypeDaoImpl implements MovieTypeDao {
    @Override
    public boolean saveOrUpdate(MovieType object) throws HibernateException {
        if(object == null) return false;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(object);
            transaction.commit();
            return true;
        } catch (HibernateException e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public List<MovieType> findAll() throws HibernateException {
        List<MovieType> movieTypeList = null;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            movieTypeList = session.createQuery("from MovieType", MovieType.class).list();
            return movieTypeList;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return movieTypeList;
        }
    }

    @Override
    public List<MovieType> findByMovieId(int movieId) throws HibernateException {
        List<MovieType> movieTypeList = null;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            movieTypeList = session.createQuery("from MovieType mt  where mt.movie.movieId = :movieId", MovieType.class)
                    .setParameter("movieId", movieId)
                    .list();
            return movieTypeList;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return movieTypeList;
        }
    }

    @Override
    public List<MovieType> findByTypeId(int typeId) throws HibernateException {
        List<MovieType> movieTypeList = null;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            movieTypeList = session.createQuery("from MovieType mt join mt.type m where m.typeId = :typeId", MovieType.class)
                    .setParameter("typeId", typeId)
                    .list();
            return movieTypeList;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return movieTypeList;
        }
    }

    @Override
    public boolean deleteByTypeId(int typeId) throws HibernateException {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            int row = session.createQuery("delete from MovieType mt where mt.type.typeId = :typeId")
                    .setParameter("typeId", typeId)
                    .executeUpdate();
            transaction.commit();
            return row > 0;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public MovieType findById(int typeId, int movieId) throws HibernateException {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            return session.createQuery("from MovieType m where m.type.typeId = :typeId and m.movie.movieId = :movieId", MovieType.class)
                    .setParameter("typeId", typeId)
                    .setParameter("movieId", movieId)
                    .getSingleResultOrNull();
        } catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

}
