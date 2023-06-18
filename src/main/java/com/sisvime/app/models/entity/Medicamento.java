package com.sisvime.app.models.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Entity
@Table(name = "medicamentos")
@Getter
@Setter
public class Medicamento implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "med_id")
    private int id;

    @NotEmpty
    @Column(name = "med_nombre")
    private String nombre;

    @NotEmpty
    @Column(name = "med_descripcion")
    private String descripcion;



}
