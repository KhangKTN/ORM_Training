package org.example;

import org.example.entities.CinemaRoom;
import org.hibernate.HibernateException;
import org.hibernate.Session;

public class App 
{
    public static void main( String[] args )
    {
        try(Session session = HibernateUtils.getSessionFactory().openSession()){
            session.beginTransaction();

            session.getTransaction().commit();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
    }
}
