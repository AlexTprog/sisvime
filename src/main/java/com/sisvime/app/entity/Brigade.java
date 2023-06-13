package com.sisvime.app.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@Getter
@Setter
public class Brigade {
    @Id
    @GeneratedValue
    private Long Id;

    @OneToOne
    @Nullable
    private Doctor Doctor;

    @OneToOne
    @Nullable
    private Nurse Nurse;

    @OneToOne
    @Nullable
    private Nurse TechnicianNurse;

    @OneToOne
    private Vehicle Vehicle;

    @OneToMany(mappedBy = "Brigade", cascade = CascadeType.ALL)
    private List<BrigadeHours> BrigadeHours = new ArrayList<BrigadeHours>();
}
