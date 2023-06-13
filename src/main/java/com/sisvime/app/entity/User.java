package com.sisvime.app.entity;

import com.sisvime.app.share.CivilState;
import com.sisvime.app.share.Gender;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table
@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    @NonNull
    private String Document;
    @NonNull
    private String Email;
    @NonNull
    private String Password;
    @NonNull
    private String Name;
    @NonNull
    private String FatherLastName;
    @NonNull
    private String MotherLastName;

    private Date Birthday;

    private String PhoneNumber;

    private String Direction;

    private Gender Gender;

    private CivilState CivilState;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "User_Rol",
            joinColumns = @JoinColumn(name = "User_Id"),
            inverseJoinColumns = @JoinColumn(name = "Rol_Id")
    )
    private List<Rol> Roles = new ArrayList<>();
}
