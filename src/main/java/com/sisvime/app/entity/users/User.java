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
    private Long id;
    @NonNull
    private String document;
    @NonNull
    private String email;
    @NonNull
    private String Password;
    @NonNull
    private String name;
    @NonNull
    private String fatherLastName;
    @NonNull
    private String motherLastName;
    @NonNull
    private LocalDateTime birthday;

    private String phoneNumber;

    private String direction;

    private Gender gender;

    private UserStatus status;

    private CivilState civilState;

    private String photo;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "User_Rol",
            joinColumns = @JoinColumn(name = "User_Id"),
            inverseJoinColumns = @JoinColumn(name = "Rol_Id")
    )
    private List<Rol> roles = new ArrayList<>();
}
