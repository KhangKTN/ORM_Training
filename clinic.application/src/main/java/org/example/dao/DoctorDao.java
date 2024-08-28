package org.example.dao;

import org.example.HibernateUtils;
import org.example.entities.Appointment;
import org.example.entities.Doctor;
import org.example.utils.Log;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class DoctorDao {
    public boolean addDoctor() {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            session.beginTransaction();
            Doctor doctor = new Doctor();
            doctor.setFirstName("Tuan");
            doctor.setLastName("Le Anh");

            List<Appointment> appointments = new ArrayList<>();
            Appointment appointment = new Appointment();
            appointment.setTime("08:00");
            appointment.setDuration(1);
            appointment.setReason("Dau tim");
            appointment.setDoctor(doctor);
            appointments.add(appointment);

            appointment = new Appointment();
            appointment.setTime("09:00");
            appointment.setDuration(1);
            appointment.setReason("Dau tim");
            appointment.setDoctor(doctor);
            appointments.add(appointment);

            appointment = new Appointment();
            appointment.setTime("10:00");
            appointment.setDuration(1);
            appointment.setReason("Dau tim");
            appointment.setDoctor(doctor);
            appointments.add(appointment);

            doctor.setAppointmentList(appointments);
            session.persist(doctor);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean deleteDoctor() {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            session.beginTransaction();
            Doctor doctor = session.get(Doctor.class, 1);
            session.remove(doctor);
            session.getTransaction().commit();
            return true;
        } catch (Exception e){
            System.out.println(e.getMessage());
            Log.error(e.getMessage());
            return false;
        }
    }

    public Doctor getDoctorById(int id) {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            return session.get(Doctor.class, id);
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
            Log.error(e.getMessage());
            return null;
        }
    }
}
