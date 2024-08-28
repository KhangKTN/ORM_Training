package org.example.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "seats")
@Getter @Setter
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int seatColumn;

    private int seatRow;

    private String status;

    private String type;

    @ManyToOne
    @JoinColumn(name = "roomId", nullable = false)
    private CinemaRoom cinemaRoom;
}
