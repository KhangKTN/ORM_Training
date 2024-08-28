package org.example.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Setter @Getter
public class LichSuTiemChung {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "khach_hang_id")
    KhachHang khachHang;

    @ManyToOne
    @JoinColumn(name = "nhan_vien_id")
    NhanVien nhanVien;

    @ManyToOne
    @JoinColumn(name = "vacxin_id")
    VacXin vacXin;

    @ManyToOne
    @JoinColumn(name = "chi_nhanh_id")
    ChiNhanh chiNhanh;

    LocalDate ngay_tiem;
    String trang_thai;
    long tong_tien;

    @Override
    public String toString() {
        return "LichSuTiemChung{" +
                "id=" + id +
                ", khachHang=" + khachHang +
                ", nhanVien=" + nhanVien +
                ", vacXin=" + vacXin +
                ", chiNhanh=" + chiNhanh +
                ", ngay_tiem=" + ngay_tiem +
                ", trang_thai='" + trang_thai + '\'' +
                ", tong_tien=" + tong_tien +
                '}';
    }
}
