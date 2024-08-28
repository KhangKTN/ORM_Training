package org.example;

import org.example.dao.*;
import org.example.entities.KhachHang;
import org.example.entities.LichSuTiemChung;
import org.example.entities.NhanVien;
import org.example.entities.VacXin;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAmount;
import java.util.List;
import java.util.Scanner;

public class App {
    static ChiNhanhDao chiNhanhDao = new ChiNhanhDao();
    static KhachHangDao khachHangDao = new KhachHangDao();
    static NhanVienDao nhanVienDao = new NhanVienDao();
    static VacxinDao vacxinDao = new VacxinDao();
    static LSTC_Dao lstcDao = new LSTC_Dao();

    public static void insertData(){
        chiNhanhDao.addNew();
        khachHangDao.addNew();
        nhanVienDao.addNew();
        vacxinDao.addNew();
    }

    public static void showKhachHangCheckin(){
        List<KhachHang> khachHangList = khachHangDao.getKhachHangCheckin();
        System.out.println(khachHangList.size());
        khachHangList.forEach(item -> System.out.println(item.getHo_ten()));
    }

    public static void phanCongTiemChung(){
        Scanner scanner = new Scanner(System.in);
        int maLSTC = 0;
        int maNV = 0;
        System.out.print("Enter ma LSTC: ");
        maLSTC = scanner.nextInt();
        scanner.skip("\n");
        System.out.print("Enter ma NV: ");
        maNV = scanner.nextInt();

        LichSuTiemChung lstc = lstcDao.getById(maLSTC);
        NhanVien nhanVien = nhanVienDao.getNhanVienById(maNV);

        LocalDate today = LocalDate.now();
        Period period = Period.between(lstc.getKhachHang().getNgay_sinh(), today);
        int yearOld = Math.abs(period.getYears());
        System.out.println("So tuoi: " + yearOld);

        if(yearOld <= 6 && nhanVien.getNam_kinh_nghiem() < 3){
            System.out.println("Nhan vien tiem chung khong phu hop");
            return;
        }
        lstc.setTrang_thai("Cho tiem");
        lstcDao.update(lstc);
    }

    public static void showLSTC(){
        Scanner scanner = new Scanner(System.in);
        List<LichSuTiemChung> lstcList = lstcDao.getAll();
        lstcList.forEach(item -> {
            System.out.println(item.toString());
        });
        int option = 0;
        while(true){
            System.out.print("Chon hang de update: ");
            option = scanner.nextInt();
            if(option > 0 && option <= lstcList.size()){
                LichSuTiemChung lstc = lstcList.get(option - 1);
                lstc.setTrang_thai("Da tiem");
                lstc.setNgay_tiem(LocalDate.now());
                long price = lstc.getVacXin().getGia();
                int tuoi = LocalDate.now().getYear() - lstc.getKhachHang().getNgay_sinh().getYear();
                if(tuoi < 6){
                    price = (long) (price - price*0.1);
                }
                else if(tuoi <= 16){
                    price = (long) (price - price*0.05);
                }
                lstc.setTong_tien(price);
                lstcDao.update(lstc);
                break;
            }
            System.out.println("Vui long chon lai");
        }
    }

    public static void main(String[] args) {
        try(Session session = HibernateUtils.getSessionFactory().openSession()){
            Scanner scanner = new Scanner(System.in);

            while(true){
                System.out.println("1. Insert data");
                System.out.println("2. Ds khach hang chekin");
                System.out.println("3. Phan cong tiem chung");
                System.out.println("4. Danh sach tiem chung");
                System.out.println("0. Exit");

                String option = scanner.nextLine();
                if("1".equals(option)) insertData();
                if("2".equals(option)) showKhachHangCheckin();
                if("3".equals(option)) phanCongTiemChung();
                if("4".equals(option)) showLSTC();
                if("0".equals(option)) break;
            }

        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
    }
}
