package org.example.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter @Setter
public class NhanVien {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ten_nhan_vien;
    private int nam_kinh_nghiem;

    @OneToMany(mappedBy = "nhanVien")
    List<LichSuTiemChung> lichSuTiemChungList;
}
