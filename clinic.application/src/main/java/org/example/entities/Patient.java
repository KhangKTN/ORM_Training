package org.example.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "patients")
@Getter @Setter
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int patientId;
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String state;

    @OneToMany(mappedBy = "patient")
    List<Appointment> appointmentList;
}
