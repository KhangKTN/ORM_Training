package org.example.dao;

import org.example.HibernateUtils;
import org.example.entities.ChiNhanh;
import org.example.entities.VacXin;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.util.Arrays;
import java.util.List;

public class VacxinDao {
    public void addNew(){
        try(Session session = HibernateUtils.getSessionFactory().openSession()) {
            List<String> tenList = Arrays.asList("Vacxin1", "Vacxin2", "Vacxin3", "Vacxin4", "Vacxin5");
            session.beginTransaction();
            for(int i = 1; i <= 5; i++){
                VacXin vacxin = new VacXin();
                vacxin.setTen(tenList.get(i-1));
                vacxin.setGia(100000*i);

                session.persist(vacxin);
            }
            session.getTransaction().commit();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
    }
}
