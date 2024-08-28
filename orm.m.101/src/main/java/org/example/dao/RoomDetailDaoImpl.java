package org.example.dao;

import org.example.HibernateUtils;
import org.example.entities.CinemaRoom;
import org.example.entities.RoomDetail;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class RoomDetailDaoImpl implements BaseDao<RoomDetail> {
    @Override
    public boolean saveOrUpdate(RoomDetail object) throws HibernateException {
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
    public RoomDetail getById(Long id) throws HibernateException {
        RoomDetail response = null;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            response = session.get(RoomDetail.class, id);
        }
        return response;
    }

    public List<RoomDetail> getAll() throws HibernateException {
        List<RoomDetail> response = null;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            response = session.createQuery("from RoomDetail").list();
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
            RoomDetail roomDetail = session.get(RoomDetail.class, id);
            if(roomDetail == null) {
                System.out.println("[INFO] CinemaRoom is not existed");
                return false;
            }
            session.delete(roomDetail);
            transaction.commit();
        }
        return true;
    }
}
