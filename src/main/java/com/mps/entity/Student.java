package com.mps.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Generated;

import java.time.LocalDate;

@Entity
@Data
@Table(name="student_tab")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "lastName")
    private String lastName;
    @Column(name = "dob")
    private LocalDate dob;
    @Column(name = "section")
    private Character section;
    @Column(name = "gender")
    private String gender;
    @Column(name = "marks1")
    private Long marks1;
    @Column(name = "marks2")
    private Long marks2;
    @Column(name = "marks3")
    private Long marks3;
    @Column(name = "total")
    private Long total;
    @Column(name = "average")
    private Long average;
    @Column(name = "result")
    private String result;
}
