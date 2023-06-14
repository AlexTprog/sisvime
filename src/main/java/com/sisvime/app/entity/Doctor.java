package com.sisvime.app.entity;


import com.sisvime.app.entity.users.User;

import javax.persistence.*;
import java.util.List;

@Table
@Entity
public class Doctor extends User {

    @ManyToOne(fetch = FetchType.LAZY)
    private Speciality Speciality;

    @OneToMany(mappedBy = "Doctor")
    private List<DoctorHours> DoctorHours;

}
