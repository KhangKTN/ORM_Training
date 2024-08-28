package org.example.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter @Setter
public class TreEm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long maTre;

    String tenTre;
    String gioiTinh;
    LocalDate ngaySinh;

    @ManyToOne
    @JoinColumn(name = "maPH")
    PhuHuynh phuHuynh;

    @OneToMany(mappedBy = "treEm")
    List<LopTreEm> lopTreEmList;
}
