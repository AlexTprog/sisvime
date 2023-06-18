package com.sisvime.app.models.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Entity
@Table(name = "enfermedades")
@Getter
@Setter
public class Enfermedad implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "enf_codigo")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty
    @Column(name = "enf_nombre")
    private String nombre;

    @NotEmpty
    @Column(name = "enf_sintoma")
    private String sintoma;

}
