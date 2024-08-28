package org.example.dao;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.example.HibernateUtils;
import org.example.entities.Bill;
import org.example.utils.Log;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.time.LocalDate;
import java.util.List;

import static org.example.HibernateUtils.sessionFactory;

public class BillDao {
    public boolean addBill(Bill bill) {
        try (Session session = HibernateUtils.getSessionFactory().openSession()){
            if(bill == null) return false;
            session.beginTransaction();
            session.persist(bill);
            session.getTransaction().commit();
            return true;
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
            Log.error(e.getMessage());
            return false;
        }
    }

    public List<Bill> getBillByDate(LocalDate date) {
        List<Bill> billList = null;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            billList = session.createQuery("from Bill where date = :date", Bill.class)
                    .setParameter("date", date)
                    .list();
            return billList;
        } catch (Exception e){
            System.out.println(e.getMessage());
            Log.error(e.getMessage());
            return billList;
        }
    }

    public List<Bill> getAllBills(int page, int size) {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Bill> criteriaQuery = criteriaBuilder.createQuery(Bill.class);

            Root<Bill> root = criteriaQuery.from(Bill.class);
            criteriaQuery.select(root);

            Query<Bill> query = session.createQuery(criteriaQuery);
            query.setFirstResult((page - 1) * size);
            query.setMaxResults(page);

            List<Bill> list = query.getResultList();
            sessionFactory.close();
            return list;
        } catch (HibernateException e){
            System.out.println(e.getMessage());
            Log.error(e.getMessage());
            return null;
        }
    }

    public Bill getBillById(int id) {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            return session.get(Bill.class, id);
        } catch (HibernateException e){
            System.out.println(e.getMessage());
            Log.error(e.getMessage());
            return null;
        }
    }
}
