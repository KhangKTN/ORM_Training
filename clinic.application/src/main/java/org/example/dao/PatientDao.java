package org.example.dao;

import jakarta.persistence.StoredProcedureQuery;
import org.example.HibernateUtils;
import org.example.entities.Patient;
import org.example.utils.Log;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class PatientDao {
    public boolean addPatient() {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            session.beginTransaction();
            Patient patient;
            for(int i = 1; i <= 5; i++){
                patient = new Patient();
                patient.setFirstName("A");
                patient.setLastName("Le");
                session.persist(patient);
                session.flush();
                session.clear();
            }
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            Log.error(e.getMessage());
            return false;
        }
    }

    public Patient getPatientById(int id) {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            Patient patient = (Patient) session.get(Patient.class, id);
            return patient;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            Log.error(e.getMessage());
            return null;
        }
    }

    public List<Patient> getAllPatients(int pagesize, int page) {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            Query query = session.createNativeQuery(
                            "CALL proc_getPatient(:pagesize, :skip)")
                    .addEntity(Patient.class)
                    .setParameter("pagesize", pagesize)
                    .setParameter("skip", (page-1)*pagesize);

            List<Patient> patients = (List<Patient>) query.getResultList();
            return patients;
        } catch (Exception e){
            System.out.println(e.getMessage());
            Log.error(e.getMessage());
            return null;
        }
    }
}
