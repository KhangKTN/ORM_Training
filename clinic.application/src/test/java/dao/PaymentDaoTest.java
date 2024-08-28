package dao;

import org.example.dao.BillDao;
import org.example.dao.PaymentDao;
import org.example.entities.Bill;
import org.example.entities.Payment;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;

public class PaymentDaoTest {
    PaymentDao paymentDao;
    BillDao billDao;

    @Before
    public void setUp() throws Exception {
        paymentDao = new PaymentDao();
        billDao = new BillDao();
    }

    @Test
    public void getPaymentByBillId(){
        List<Payment> paymentList = paymentDao.findByBillId(3);
        paymentList.forEach(item -> System.out.println(item.getPaymentId()));
        assertFalse(paymentList.isEmpty());
    }

    @Test
    public void addPayment(){
        int billId = 10;
        Bill bill = billDao.getBillById(billId);
        if(bill == null){
            System.out.println("Bill not found!");
            return;
        }
        Payment payment = new Payment();
        payment.setDate(LocalDate.now());
        payment.setAmount(100000);
        payment.setMethod("Banking");
        payment.setBill(bill);
        paymentDao.addPayment(payment);

        // payment = new Payment();
        payment.setDate(LocalDate.now());
        payment.setAmount(150000);
        payment.setMethod("Cash");
        payment.setBill(bill);
        paymentDao.addPayment(payment);
    }
}
