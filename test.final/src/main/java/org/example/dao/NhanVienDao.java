package org.example.dao;

import org.example.HibernateUtils;
import org.example.entities.ChiNhanh;
import org.example.entities.NhanVien;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.util.Arrays;
import java.util.List;

public class NhanVienDao {
    public void addNew(){
        try(Session session = HibernateUtils.getSessionFactory().openSession()) {
            List<String> tenList = Arrays.asList("NV1", "NV2", "NV3", "NV4", "NV5", "NV6");
            session.beginTransaction();
            for(int i = 1; i <= 6; i++){
                NhanVien nv = new NhanVien();
                nv.setTen_nhan_vien(tenList.get(i-1));
                nv.setNam_kinh_nghiem(i);

                session.persist(nv);
            }
            session.getTransaction().commit();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
    }

    public NhanVien getNhanVienById(int id){
        NhanVien nv = null;
        try(Session session = HibernateUtils.getSessionFactory().openSession()) {
            nv = session.get(NhanVien.class, id);
            return nv;
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
            return nv;
        }
    }
}
