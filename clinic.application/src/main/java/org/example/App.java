package org.example;

import org.hibernate.HibernateException;
import org.hibernate.Session;

public class App {
    public static void main(String[] args) {
        try(Session session = HibernateUtils.getSessionFactory().openSession()){
            System.out.println("RUN");
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
    }
}
