package org.example;

import org.example.dao.LopDao;
import org.example.dao.LopTreEmDao;
import org.example.dao.PhuHuynhDao;
import org.example.dao.TreEmDao;
import org.example.entities.Lop;
import org.example.entities.LopTreEm;
import org.example.entities.PhuHuynh;
import org.example.entities.TreEm;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class App {
    static Scanner scanner = new Scanner(System.in);
    static PhuHuynhDao phuHuynhDao = new PhuHuynhDao();
    static TreEmDao treEmDao = new TreEmDao();
    static LopDao lopDao = new LopDao();
    static LopTreEmDao lopTreEmDao = new LopTreEmDao();

    public static void insertData(){
        phuHuynhDao.addList();
        treEmDao.addList();
        lopDao.addList();
        lopTreEmDao.addList();
    }

    public static void addPhuHuynh(){
        for(int i = 1; i <= 2; i++){
            PhuHuynh phuHuynh = new PhuHuynh();
            System.out.print("Nhap ten PH: ");
            phuHuynh.setTenPH(scanner.nextLine());
            System.out.print("Nhap SDT: ");
            phuHuynh.setSoDienThoai(scanner.nextLine());

            if(phuHuynhDao.addOne(phuHuynh)) System.out.println("Add Phu Huynh successfully!");
            else System.out.println("Add Phu Huynh failed!");
        }
    }

    public static void addTreEm(){
        PhuHuynh phuHuynh = null;
        for(int i = 0; i <= 4; i++){
            TreEm treEm = new TreEm();
            System.out.print("Nhap ten Tre em: ");
            treEm.setTenTre(scanner.nextLine());
            System.out.print("Nhap gioi tinh: ");
            treEm.setGioiTinh(scanner.nextLine());
            System.out.print("Nhap ngay sinh: ");
            String dateString = scanner.nextLine();
            treEm.setNgaySinh(LocalDate.parse(dateString));

            while(true){
                System.out.print("Nhap MaPH: ");
                int maPH = scanner.nextInt();
                scanner.nextLine();
                phuHuynh = phuHuynhDao.getById(maPH);
                if(phuHuynh != null) {
                    treEm.setPhuHuynh(phuHuynh);
                    treEmDao.addOne(treEm);
                    break;
                }
                System.out.println("Phu Huynh khong ton tai!");
            }
        }
    }

    public static void addLop(){
        for(int i = 1; i <= 2; i++){
            Lop lop = new Lop();
            System.out.print("Nhap ten lop: ");
            lop.setTenLop(scanner.nextLine());
            System.out.print("Nhap trang thai: ");
            lop.setTrangThai(scanner.nextLine());
            System.out.print("Nhap so luong toi da: ");
            lop.setSoLuongToiDa(scanner.nextInt());
            scanner.nextLine();
            System.out.print("Nhap so luong con lai: ");
            lop.setSoLuongConLai(scanner.nextInt());
            scanner.nextLine();
            System.out.print("Nhap ngay bat dau (yyyy-MM-dd): ");
            lop.setNgayBatDau(LocalDate.parse(scanner.nextLine()));
            System.out.print("Ngay ket thuc (yyyy-MM-dd): ");
            lop.setNgayKetThuc(LocalDate.parse(scanner.nextLine()));

            lopDao.addOne(lop);
        }
    }

    public static void addLopTreEm(){
        for(int i = 1; i <= 3; i++){
            LopTreEm lopTreEm = new LopTreEm();
            Lop lop;
            TreEm treEm;
            int maLop;
            int maTreem;

            while (true){
                System.out.print("Nhap ma lop: ");
                maLop = scanner.nextInt();
                scanner.nextLine();
                lop = lopDao.getById(maLop);
                if(lop != null) {
                    lopTreEm.setLop(lop);
                    break;
                }
                System.out.print("Lop khong ton tai!");
            }

            while(true){
                System.out.print("Nhap ma tre em: ");
                maTreem = scanner.nextInt();
                scanner.nextLine();
                treEm = treEmDao.getById(maTreem);
                if(treEm != null) {
                    lopTreEm.setTreEm(treEm);
                }
                else {
                    System.out.println("Tre em khong ton tai!");
                    continue;
                }
                if(lopTreEmDao.findLopTreEmByLopIdAndTreEmId(maLop, maTreem) == null) {
                    break;
                }
                System.out.println("Tre da ton tai trong lop");
            }
            System.out.print("Nhap trang thai: ");
            lopTreEm.setTrangThai(scanner.nextLine());
            System.out.print("Nhap ngay vao lop: ");
            lopTreEm.setNgayVaoLop(LocalDate.parse(scanner.nextLine()));
            System.out.print("Nhap ngay kt thuc: ");
            lopTreEm.setNgayKetThucHoc(LocalDate.parse(scanner.nextLine()));

            lopTreEmDao.addOne(lopTreEm);
        }
    }

    public static void hienThiLopTreEm(){
        List<LopTreEm> lopTreEmList = lopTreEmDao.findByTrangThai("DA HOC XONG");
        for(LopTreEm lopTreEm : lopTreEmList){
            System.out.println("Ma tre: " + lopTreEm.getTreEm().getMaTre());
            System.out.println("Ten tre: " + lopTreEm.getTreEm().getTenTre());
            System.out.println("Ma lop: " + lopTreEm.getLop().getMaLop());
            System.out.println("Ten PH: " + lopTreEm.getTreEm().getPhuHuynh().getTenPH());
            System.out.println("Moi quan he: " + lopTreEm.getTreEm().getPhuHuynh().getMoiQuanHe());
            System.out.println("Trang thai: " + lopTreEm.getTrangThai());
            System.out.println("--------------------");
        }
    }

    public static void updateSoluongAndTrangThai(){
        List<Lop> lopList = lopDao.getAll();
        for(Lop lop : lopList){
            int sl = lopTreEmDao.countTreEmByLopId(lop.getMaLop());
            if(sl > 0){
                lopDao.updateSoLuong(sl, lop.getMaLop());
            }
            if(sl == lop.getSoLuongToiDa()){
                lopDao.updateTrangThai("CLOSED", lop.getMaLop());
            }
        }
    }

    public static void deleteLop(){
        List<Lop> lopList = lopDao.getAll();
        for(Lop lop : lopList){
            int sl = lopTreEmDao.countTreEmByLopId(lop.getMaLop());
            if(sl == 0){
                if(lopDao.delete(lop.getMaLop()))
                    System.out.println("Delete successfully " + lop.getMaLop());
            }
        }
    }

    public static void main(String[] args) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            while(true){
                System.out.println("1. Insert data");
                System.out.println("2. Them 2 phu huynh");
                System.out.println("3. Them tre em");
                System.out.println("4. Them lop");
                System.out.println("5. Them lop tre em");
                System.out.println("6. Hien thi lop tre em");
                System.out.println("7. Update Lop");
                System.out.println("8. Delete Lop");
                System.out.println("0. Exit");

                String option = scanner.nextLine();
                if("1".equals(option)) insertData();
                else if("2".equals(option)) addPhuHuynh();
                else if("3".equals(option)) addTreEm();
                else if("4".equals(option)) addLop();
                else if("5".equals(option)) addLopTreEm();
                else if("6".equals(option)) hienThiLopTreEm();
                else if("7".equals(option)) updateSoluongAndTrangThai();
                else if ("8".equals(option)) deleteLop();
                else if("0".equals(option)) break;
                else System.out.println("Invalid option");
            }
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
    }
}
