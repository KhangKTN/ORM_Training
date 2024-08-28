package org.example.dao;

import org.example.HibernateUtil;
import org.example.entities.Lop;
import org.example.entities.LopTreEm;
import org.example.entities.TreEm;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LopTreEmDao {
    public boolean addOne(LopTreEm lopTreEm) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            session.beginTransaction();
            session.persist(lopTreEm);
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
            LopDao lopDao = new LopDao();
            TreEmDao treEmDao = new TreEmDao();

            Lop lop = lopDao.getById(1);
            Lop lop1 = lopDao.getById(2);

            List<TreEm> treEmList = new ArrayList<>();
            for(int i = 1; i <= 6; i++){
                TreEm treEm = treEmDao.getById(i);
                treEmList.add(treEm);
            }
            int j = 1;

            for(int i = 1; i <= 10; i++){
                TreEm tem = treEmDao.getById(i);

                LopTreEm lopTreem = new LopTreEm();
                lopTreem.setNgayVaoLop(LocalDate.now());
                lopTreem.setNgayKetThucHoc(LocalDate.now().plusDays(180));

                if(i <= 6) lopTreem.setLop(lop);
                else lopTreem.setLop(lop1);

                // lopTreem.setTreEm(treEmDao.getById(j));
                lopTreem.setTreEm(treEmList.get(j-1));
                j++;
                if(j == 7) j = 1;
                lopTreem.setTrangThai(i % 2 == 0 ? "DANG HOC" : "DA NGHI");
                if(i % 3 == 0) lopTreem.setTrangThai("DA HOC XONG");

                session.persist(lopTreem);
            }
            session.getTransaction().commit();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
    }

    public LopTreEm findLopTreEmByLopIdAndTreEmId(int lopId, int treEmId){
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            return session.createQuery("from LopTreEm l where l.lop.maLop = :lopId AND l.treEm.maTre = :treEmId", LopTreEm.class)
                    .setParameter("lopId", lopId)
                    .setParameter("treEmId", treEmId)
                    .getSingleResultOrNull();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public int countTreEmByLopId(long lopId){
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            return session.createQuery("from LopTreEm l where l.lop.maLop = :lopId", LopTreEm.class)
                    .setParameter("lopId", lopId).list().size();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return -1;
        }
    }

    public List<LopTreEm> findByTrangThai(String trangThai){
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            return session.createQuery("from LopTreEm where trangThai = :tt And ngayKetThucHoc BETWEEN :date1 AND :date2", LopTreEm.class)
                    .setParameter("tt", trangThai)
                    .setParameter("date1", LocalDate.of(2022, 5, 1))
                    .setParameter("date2", LocalDate.now())
                    .list();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
