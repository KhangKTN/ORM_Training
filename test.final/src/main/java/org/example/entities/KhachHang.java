package org.example.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Setter @Getter
public class KhachHang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    String ho_ten;
    String dien_thoai;

    LocalDate ngay_sinh;

    @OneToMany(mappedBy = "khachHang")
    List<LichSuTiemChung> lichSuTiemChungList;
}
