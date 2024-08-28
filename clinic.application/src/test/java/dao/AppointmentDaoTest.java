package dao;

import org.example.dao.AppointmentDao;
import org.example.dao.DoctorDao;
import org.example.dao.PatientDao;
import org.example.entities.Appointment;
import org.example.entities.Doctor;
import org.example.entities.Patient;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class AppointmentDaoTest {
    AppointmentDao appointmentDao;
    PatientDao patientDao;
    DoctorDao doctorDao;
    Appointment appointment;

    @Before
    public void setUp() throws Exception {
        appointmentDao = new AppointmentDao();
        patientDao = new PatientDao();
        doctorDao = new DoctorDao();
        appointment = new Appointment();
    }

    @Test
    public void addAppointment() {
        // Check exist appointment
        int patientId = 3;
        int doctorId = 1;
        appointment = appointmentDao.getAppointmentByPatientIdAndDate(patientId, LocalDate.now());

        if(appointment == null) {
            appointment = new Appointment();
        }
        Patient patient = patientDao.getPatientById(patientId);
        if(patient == null) {
            System.out.println("Patient not found!");
            return;
        }
        Doctor doctor = doctorDao.getDoctorById(doctorId);
        if(doctor == null) {
            System.out.println("Doctor not found!");
            return;
        }
        appointment.setDoctor(doctor);
        appointment.setPatient(patient);
        appointment.setDuration(1);
        appointment.setTime("09:00");
        appointment.setDate(LocalDate.now());
        appointment.setReason("Dau dau");

        assertTrue(appointmentDao.addAppointment(appointment));
    }
}
