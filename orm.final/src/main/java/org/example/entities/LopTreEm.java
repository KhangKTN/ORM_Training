package org.example.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Setter @Getter
public class LopTreEm {
    @Id
    @ManyToOne
    @JoinColumn(name = "maLop")
    private Lop lop;

    @Id
    @ManyToOne
    @JoinColumn(name = "maTre")
    private TreEm treEm;

    String trangThai;
    LocalDate ngayVaoLop;
    LocalDate ngayKetThucHoc;
}
