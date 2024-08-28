package org.example.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter @Setter
public class ChiNhanh {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    String ten_chi_nhanh;

    @OneToMany(mappedBy = "chiNhanh")
    List<LichSuTiemChung> lichSuTiemChungList;
}
