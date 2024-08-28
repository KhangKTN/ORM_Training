package org.example.entities;

import jakarta.persistence.*;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "bills")
@Setter
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int billId;
    private LocalDate date;
    private String status;

    @OneToMany(mappedBy = "bill")
    List<Payment> paymentList;

    @OneToOne
    @JoinColumn(name = "appointment_id")
    private Appointment appointment;
}
