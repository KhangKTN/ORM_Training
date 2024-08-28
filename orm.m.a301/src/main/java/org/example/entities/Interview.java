package org.example.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "interviews")
public class Interview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int interview_id;

    private String time;
    private LocalDate date;
    private String interviewer;
    private String comments;
    private String interview_result;
    private String remark;

    @ManyToOne
    @JoinColumn(name = "candidate_id")
    private Candidate candidate;
}
