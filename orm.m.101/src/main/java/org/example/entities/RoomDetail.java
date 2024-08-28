package org.example.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Entity
@Table(name = "room_details")
@Getter @Setter
@ToString
public class RoomDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "roomId", nullable = false)
    private CinemaRoom cinemaRoom;

    private int roomRate;

    private LocalDate activeDate;

    private String description;
}
