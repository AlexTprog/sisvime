package com.sisvime.app.entity;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Table
@Entity
@Getter
@Setter
public class MedicalVisit {
    @Id
    @GeneratedValue
    private Long Id;

    private String Type;

    private String Observation;

    @NotNull
    private String District;

    @NotNull
    private String Zone;

    @NotNull
    private LocalDateTime Date;

    private LocalDateTime InitDate;
    private Integer DurationVisit;//Seconds

    @OneToOne
    private Vehicle Vehicle;

}
