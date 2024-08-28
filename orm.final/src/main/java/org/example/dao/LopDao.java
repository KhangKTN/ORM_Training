package org.example.dao;

import org.example.HibernateUtil;
import org.example.entities.Lop;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.time.LocalDate;
import java.util.List;

public class LopDao {
    public boolean addOne(Lop lop) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.persist(lop);
            session.getTransaction().commit();
            return true;
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public void addList(){
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();

            for(int i = 1; i <= 6; i++){
                Lop lop = new Lop();
                lop.setTenLop("Lop " + i);
                lop.setNgayBatDau(LocalDate.now());
                lop.setNgayKetThuc(LocalDate.now().plusDays(180));
                lop.setSoLuongConLai(0);
                lop.setSoLuongToiDa(10);
                lop.setTrangThai(i % 2 == 0 ? "OPEN" : "CLOSED");

                session.persist(lop);
            }
            session.getTransaction().commit();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
    }

    public Lop getById(int id){
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Lop.class, id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public boolean updateSoLuong(int sl, long maLop){
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            int row = session.createQuery("update Lop set soLuongConLai = :sl where maLop = :maLop")
                .setParameter("sl", sl)
                .setParameter("maLop", maLop)
                .executeUpdate();
                session.getTransaction().commit();

            return row > 0;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean updateTrangThai(String trangThai, long maLop){
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            int row = session.createQuery("update Lop set trangThai = :tt where maLop = :maLop")
                    .setParameter("tt", trangThai)
                    .setParameter("maLop", maLop)
                    .executeUpdate();
            session.getTransaction().commit();
            return row > 0;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public List<Lop> getAll(){
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Lop", Lop.class).list();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public boolean delete(long id){
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            Lop lop = session.get(Lop.class, id);
            session.remove(lop);
            session.getTransaction().commit();
            return true;
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
