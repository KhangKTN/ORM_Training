package org.example.dao;

import org.example.HibernateUtils;
import org.example.entities.Payment;
import org.example.utils.Log;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.util.List;

public class PaymentDao {
    public List<Payment> findByBillId(int billId) {
        List<Payment> paymentList = null;
        try(Session session = HibernateUtils.getSessionFactory().openSession()){
            paymentList = session.createQuery("from Payment p join p.bill b where b.billId = :billId", Payment.class)
                    .setParameter("billId", billId).list();
            return paymentList;
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
            Log.error(e.getMessage());
            return null;
        }
    }

    public boolean addPayment(Payment payment) {
        try(Session session = HibernateUtils.getSessionFactory().openSession()){
            if(payment == null) return false;
            if(payment.getBill() == null) return false;
            session.beginTransaction();
            session.persist(payment);
            session.getTransaction().commit();
            return true;
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
            Log.error(e.getMessage());
            return false;
        }
    }
}
