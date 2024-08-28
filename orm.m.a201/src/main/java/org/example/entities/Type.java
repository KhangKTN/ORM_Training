package org.example.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "types")
@Getter @Setter
public class Type {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long typeId;

    private String name;
    private String description;

    @OneToMany(mappedBy = "type", cascade = CascadeType.REMOVE)
    private List<MovieType> movieTypeList;
}
