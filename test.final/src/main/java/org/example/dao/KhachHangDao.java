package org.example.dao;

import org.example.HibernateUtils;
import org.example.entities.ChiNhanh;
import org.example.entities.KhachHang;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class KhachHangDao {
    public void addNew(){
        try(Session session = HibernateUtils.getSessionFactory().openSession()) {
            List<String> tenKHList = Arrays.asList("Le Van A", "Le Van B", "Le Van C", "Le Van D", "Le Van E");
            String phone = "012345678";

            session.beginTransaction();
            for(int i = 1; i <= 5; i++){
                KhachHang kh = new KhachHang();
                kh.setHo_ten(tenKHList.get(i-1));
                kh.setDien_thoai(phone + i);
                kh.setNgay_sinh(LocalDate.now());

                session.persist(kh);
            }
            session.getTransaction().commit();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<KhachHang> getKhachHangCheckin(){
        List<KhachHang> khachHangList = new ArrayList<>();
        try(Session session = HibernateUtils.getSessionFactory().openSession()) {
            khachHangList = session.createQuery("select k from LichSuTiemChung l right join l.khachHang k where l.id = null or l.ngay_tiem != :date", KhachHang.class)
                    .setParameter("date", LocalDate.now())
                    .list();
            return khachHangList;
        } catch (Exception e){
            System.out.println(e.getMessage());
            return khachHangList;
        }
    }
}
