package org.example.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

import java.time.LocalDate;

@Entity
@Table(name = "entryTests")
public class EntryTest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int test_id;
    private String time;
    private LocalDate date;
    private String language_valuator;

    @Column(columnDefinition = "DECIMAL(4, 2)")
    @Min(0) @Max(10)
    private float language_result;
    private String technical_valuator;

    @Column(columnDefinition = "DECIMAL(4, 2)")
    @Min(0) @Max(10)
    private float technical_result;


    private String result;
    private String remark;
    private String entry_test_skill;

    @ManyToOne
    @JoinColumn(name = "candidate_id")
    private Candidate candidate;
}
