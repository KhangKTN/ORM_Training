package dao;

import org.example.dao.PatientDao;
import org.example.entities.Patient;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class PatientDaoTest {
    PatientDao patientDao;

    @Before
    public void setUp(){
        patientDao = new PatientDao();
    }

    @Test
    public void addPatient(){
        assertTrue(patientDao.addPatient());
    }

    @Test
    public void getAllPatients(){
        List<Patient> patients = patientDao.getAllPatients(2, 1);
        for(Patient patient : patients){
            System.out.println(patient.getPatientId());
        }
    }
}
