package org.example.dao.impl;

import org.example.HibernateUtils;
import org.example.dao.BaseDao;
import org.example.entities.Movie;
import org.example.entities.Type;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class TypeDaoImpl implements BaseDao<Type> {
    @Override
    public boolean saveOrUpdate(Type object) throws HibernateException {
        if(object == null) return false;

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
    public Type getById(Long id) throws HibernateException {
        Type object = null;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            object = session.get(Type.class, id);
            return object;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return object;
        }
    }

    @Override
    public boolean deleteById(int id) throws HibernateException {
        if (id < 0) {
            return false;
        }
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Type type = session.get(Type.class, id);
            if (type == null) {
                System.out.println("[INFO] Type is not existed");
                return false;
            }
            session.delete(type);
            transaction.commit();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public List<Type> getAll() {
        List<Type> typeList = null;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            typeList = session.createQuery("from Type", Type.class).list();
            return typeList;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return typeList;
        }
    }
}
