package org.example.dao;

import org.example.HibernateUtil;
import org.example.entities.PhuHuynh;
import org.example.entities.TreEm;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.time.LocalDate;

public class TreEmDao {
    public boolean addOne(TreEm treEm) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            session.beginTransaction();
            session.persist(treEm);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }


    public void addList(){
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            PhuHuynhDao phuHuynhDao = new PhuHuynhDao();
            PhuHuynh phuHuynh = phuHuynhDao.getById(1);

            for(int i = 1; i <= 6; i++){
                TreEm treEm = new TreEm();
                treEm.setTenTre("Tre Em " + i);
                treEm.setGioiTinh(i % 2 == 0 ? "Nam" : "Nu");
                treEm.setNgaySinh(LocalDate.of(2018,1,1));
                treEm.setPhuHuynh(phuHuynh);

                session.persist(treEm);
            }
            session.getTransaction().commit();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
    }

    public TreEm getById(int id){
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(TreEm.class, id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
