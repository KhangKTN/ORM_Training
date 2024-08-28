package org.example.dao;

import org.example.HibernateUtils;
import org.example.entities.ChiNhanh;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.util.Arrays;
import java.util.List;

public class ChiNhanhDao {
    public void addNew() {
        try(Session session = HibernateUtils.getSessionFactory().openSession()) {
            List<String> tenList = Arrays.asList("CN1", "CN2", "CN3", "CN4", "CN5");
            session.beginTransaction();
            for(int i = 1; i <= 5; i++){
                ChiNhanh ch = new ChiNhanh();
                ch.setTen_chi_nhanh(tenList.get(i-1));
                session.persist(ch);
            }
            session.getTransaction().commit();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
    }
}
