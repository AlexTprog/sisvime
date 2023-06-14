package com.sisvime.app.entity.users;

import com.sisvime.app.entity.roles.Rol;
import com.sisvime.app.share.CivilState;
import com.sisvime.app.share.Gender;
import com.sisvime.app.share.UserStatus;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
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

    private LocalDateTime Birthday;

    private String PhoneNumber;

    private String Direction;

    private Gender Gender;

    private UserStatus Status;

    private CivilState CivilState;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "User_Rol",
            joinColumns = @JoinColumn(name = "User_Id"),
            inverseJoinColumns = @JoinColumn(name = "Rol_Id")
    )
    private List<Rol> Roles = new ArrayList<>();
}
