package org.example.dao;

import org.example.HibernateUtils;
import org.example.entities.CinemaRoom;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class CinemaRoomDaoImpl implements BaseDao<CinemaRoom> {
    @Override
    public boolean saveOrUpdate(CinemaRoom object) throws HibernateException {
        if(object == null) {
            return false;
        }

        try(Session session = HibernateUtils.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            session.save(object);
            transaction.commit();
            return true;
        }
    }

    @Override
    public CinemaRoom getById(Long id) throws HibernateException {
        CinemaRoom response = null;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            response = session.get(CinemaRoom.class, id);
        }
        return response;
    }

    @Override
    public boolean deleteById(int id) throws HibernateException {
        if(id < 0) {
            return false;
        }
        try(Session session = HibernateUtils.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            CinemaRoom cinemaRoom = session.get(CinemaRoom.class, id);
            if(cinemaRoom == null) {
                System.out.println("[INFO] CinemaRoom is not existed");
                return false;
            }
            session.delete(cinemaRoom);
            transaction.commit();
        }
        return true;
    }
}
