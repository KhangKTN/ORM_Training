package org.example.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter @Setter
public class PhuHuynh {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long maPH;

    String tenPH;
    String diaChi;
    String soDienThoai;
    String moiQuanHe;

    @OneToMany(mappedBy = "phuHuynh")
    List<TreEm> treEmList;
}
