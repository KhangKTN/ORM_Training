package org.example.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "candidates")
@Getter @Setter
public class Candidate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int candidate_id;

    @Column(nullable = false)
    private String full_name;

    @Column(nullable = false)
    private LocalDate date_of_birth;

    @Min(0) @Max(1)
    private int gender;

    @Column(nullable = false)
    private LocalDate graduation_year;

    @Column(nullable = false, unique = true)
    private String phone;

    @Column(nullable = false, unique = true)
    private String email;
    private String skill;
    private String foreign_language;

    @Min(0) @Max(7)
    private int level;
    private String cv;
    private int allocation_status;
    private String remark;

    @OneToMany(mappedBy = "candidate")
    List<Interview> interviewList;

    @OneToMany(mappedBy = "candidate")
    List<EntryTest> entryTestList;

    @Override
    public String toString() {
        return "Candidate{" +
                "candidate_id=" + candidate_id +
                ", full_name='" + full_name + '\'' +
                ", date_of_birth=" + date_of_birth +
                ", gender=" + gender +
                ", graduation_year=" + graduation_year +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", skill='" + skill + '\'' +
                ", foreign_language='" + foreign_language + '\'' +
                ", level=" + level +
                ", cv='" + cv + '\'' +
                ", allocation_status=" + allocation_status +
                ", remark='" + remark + '\'' +
                '}';
    }
}
