package dao;

import org.example.dao.AppointmentDao;
import org.example.dao.BillDao;
import org.example.entities.Appointment;
import org.example.entities.Bill;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;

public class BillDaoTest {
    BillDao billDao;
    AppointmentDao appointmentDao;

    @Before
    public void setUp(){
        billDao = new BillDao();
        appointmentDao = new AppointmentDao();
    }

    @Test
    public void getBillByDate() {
        LocalDate date = LocalDate.of(2020, 1, 1);
        List<Bill> bills = billDao.getBillByDate(date);
        assertFalse(bills.isEmpty());
    }

    @Test
    public void addBill(){
        Bill bill = new Bill();
        Appointment appointment = appointmentDao.getAppointmentById(4);
        bill.setAppointment(appointment);
        bill.setDate(LocalDate.now());
        assertTrue(billDao.addBill(bill));
    }

    @Test
    public void getAllBills(){
        int page = 1;
        int size = 10;
        List<Bill> bills = billDao.getAllBills(page, size);
        System.out.println(bills.size());
        assertFalse(bills.isEmpty());
        assertEquals(bills.size(), size);
    }

}
