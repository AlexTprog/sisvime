package com.sisvime.app.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Table
@Entity
@Getter
@Setter
public class DoctorHours {
    @Id
    @GeneratedValue
    private Long Id;

    private LocalDate DateWork;

    private String Detail;

    @ManyToOne
    private Doctor Doctor;

}
