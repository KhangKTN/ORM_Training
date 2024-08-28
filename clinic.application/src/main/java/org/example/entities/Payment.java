package org.example.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "payments")
@Getter @Setter
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int paymentId;
    private LocalDate date;
    private String method;
    private double amount;

    @ManyToOne
    @JoinColumn(name = "payment_id")
    private Bill bill;
}
