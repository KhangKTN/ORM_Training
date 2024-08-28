package org.example.dao;

import org.example.HibernateUtils;
import org.example.entities.LichSuTiemChung;
import org.hibernate.Session;

import java.util.List;

public class LSTC_Dao {
    public LichSuTiemChung getById(int id) {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            return session.get(LichSuTiemChung.class, id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public boolean update(LichSuTiemChung lichSuTiemChung) {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.update(lichSuTiemChung);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public List<LichSuTiemChung> getAll() {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            return session.createQuery("from LichSuTiemChung").list();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
