package com.sisvime.app.entity;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Table
@Entity
@Getter
@Setter
public class Speciality {

    @Id
    @GeneratedValue
    private Long Id;

    @NotNull
    private String Name;

    @OneToMany(mappedBy = "Speciality", cascade = CascadeType.ALL)
    private List<Doctor> Doctor = new ArrayList<Doctor>();
}
