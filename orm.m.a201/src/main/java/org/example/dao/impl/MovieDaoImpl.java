package org.example.dao.impl;

import org.example.HibernateUtils;
import org.example.dao.BaseDao;
import org.example.entities.Movie;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class MovieDaoImpl implements BaseDao<Movie> {

    @Override
    public boolean saveOrUpdate(Movie object) throws HibernateException {
        if (object == null) {
            return false;
        }
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(object);
            transaction.commit();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public Movie getById(Long id) throws HibernateException {
        Movie response = null;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            response = session.get(Movie.class, id);
        }
        return response;
    }

    @Override
    public boolean deleteById(int id) throws HibernateException {
        if (id < 0) {
            return false;
        }
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Movie movie = session.get(Movie.class, id);
            if (movie == null) {
                System.out.println("[INFO] Movie is not existed");
                return false;
            }
            session.delete(movie);
            transaction.commit();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public List<Movie> getAll() {
        List<Movie> movieList = null;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            movieList = session.createQuery("from Movie", Movie.class).list();
            transaction.commit();
            return movieList;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return movieList;
        }
    }
}
