package org.example.dao;

import org.example.HibernateUtils;
import org.example.entities.Seat;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class SeatDaoImpl implements BaseDao<Seat>{
    @Override
    public boolean saveOrUpdate(Seat object) throws HibernateException {
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
    public Seat getById(Long id) throws HibernateException {
        Seat response = null;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            response = session.get(Seat.class, id);
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
            Seat seat = session.get(Seat.class, id);
            if(seat == null) {
                System.out.println("[INFO] Seat is not existed");
                return false;
            }
            session.delete(seat);
            transaction.commit();
        }
        return true;
    }
}
