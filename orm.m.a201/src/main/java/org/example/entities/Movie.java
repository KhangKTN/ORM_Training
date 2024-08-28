package org.example.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "movies")
@Getter @Setter
@ToString
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int movieId;

    private String actor;

    @Column(length = 1000)
    private String content;
    private String director;

    @Column(columnDefinition = "Decimal(5,2)")
    private BigDecimal duration;

    private LocalDate fromDate;
    private LocalDate toDate;
    private String movieProductionCompany;
    private String version;
    private String movieNameEng;
    private String movieNameVn;
    private String largeImage;
    private String smallImage;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    private List<MovieType> movieTypeList;
}
