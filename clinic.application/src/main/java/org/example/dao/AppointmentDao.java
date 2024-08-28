package org.example.dao;

import org.example.HibernateUtils;
import org.example.entities.Appointment;
import org.example.utils.Log;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.time.LocalDate;


public class AppointmentDao {
    public Appointment getAppointmentById(int id){
        try (Session session = HibernateUtils.getSessionFactory().openSession()){
            Appointment appointment = (Appointment) session.get(Appointment.class, id);
            return appointment;
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
            Log.error(e.getMessage());
            return null;
        }
    }

    public Appointment getAppointmentByPatientIdAndDate(int patientId, LocalDate date){
        try (Session session = HibernateUtils.getSessionFactory().openSession()){
            Appointment appointment = session.createQuery("from Appointment a join a.patient p where p.patientId = :patientId and a.date = :date", Appointment.class)
                    .setParameter("patientId", patientId)
                    .setParameter("date", date)
                    .getSingleResultOrNull();
            return appointment;
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
            Log.error(e.getMessage());
            return null;
        }
    }

    public boolean addAppointment(Appointment appointment){
        try (Session session = HibernateUtils.getSessionFactory().openSession()){
            if(appointment == null) return false;
            session.beginTransaction();
            session.saveOrUpdate(appointment);
            session.getTransaction().commit();
            return true;
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
            Log.error(e.getMessage());
            return false;
        }
    }
}
