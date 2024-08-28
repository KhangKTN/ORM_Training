package org.example.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "movie_type")
@Getter @Setter
public class MovieType {
    @Id
    @ManyToOne
    @JoinColumn(name = "typeId")
    private Type type;

    @Id
    @ManyToOne
    @JoinColumn(name = "movieId")
    private Movie movie;

    private String mtDescription;
}
