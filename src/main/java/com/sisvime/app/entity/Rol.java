package com.sisvime.app.entity;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@Getter
@Setter
public class Rol {
    @Id
    @GeneratedValue
    private Long Id;
    @NonNull
    private String Name;

    @ManyToMany(mappedBy = "Roles")
    private List<User> Users = new ArrayList<>();


}
