package org.example.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter @Setter
public class Lop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long maLop;

    String tenLop;
    int soLuongToiDa;
    int soLuongConLai;
    String trangThai;
    LocalDate ngayBatDau;
    LocalDate ngayKetThuc;

    @OneToMany(mappedBy = "lop")
    List<LopTreEm> lopTreEmList;
}
