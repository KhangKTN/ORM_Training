package org.example.dao;

import org.example.HibernateUtil;
import org.example.entities.PhuHuynh;
import org.hibernate.HibernateException;
import org.hibernate.Session;

public class PhuHuynhDao {
    public boolean addOne(PhuHuynh phuHuynh) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            session.beginTransaction();
            session.persist(phuHuynh);
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
                PhuHuynh phuHuynh = new PhuHuynh();
                phuHuynh.setTenPH("PH" + i);
                phuHuynh.setDiaChi("DiaChi");
                phuHuynh.setSoDienThoai("012345678" + i);
                phuHuynh.setMoiQuanHe(i % 2 == 0 ? "Cha" : "Me");
                session.persist(phuHuynh);
            }
            session.getTransaction().commit();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
    }

    public PhuHuynh getById(int id){
        PhuHuynh ph = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            ph = session.get(PhuHuynh.class, id);
            return ph;
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
            return ph;
        }
    }
}
