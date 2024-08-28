package dao;

import org.example.dao.DoctorDao;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DoctorDaoTest {
    private DoctorDao doctorDao;

    @Before
    public void setUp(){
        doctorDao = new DoctorDao();
    }

    @Test
    public void addDoctorAndAppointment(){
        assertTrue(doctorDao.addDoctor());
    }

    @Test
    public void deleteDoctor(){
        assertTrue(doctorDao.deleteDoctor());
    }
}
