package org.example.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter @Setter
public class VacXin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    String ten;
    long gia;

    @OneToMany(mappedBy = "vacXin")
    List<LichSuTiemChung> lichSuTiemChungList;
}
