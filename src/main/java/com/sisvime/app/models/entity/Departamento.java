package com.sisvime.app.models.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "departamentos")
@Getter
@Setter
public class Departamento implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "dep_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "dep_nombre")
    private String nombredep;

}
