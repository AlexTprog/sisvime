package com.sisvime.app.entity.roles;

import com.sisvime.app.entity.users.User;
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
    private Long id;
    @NonNull
    private String name;

    @ManyToMany(mappedBy = "roles")
    private List<User> users = new ArrayList<>();


}
