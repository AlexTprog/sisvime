package com.sisvime.app.models.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Entity
@Table(name = "medicamentos")
public class Medicamento implements Serializable {


    private static final long serialVersionUID = 1L;


    @Id
    @Column(name = "med_id")
    private int id;

    @NotEmpty
    @Column(name = "med_nombre")
    private String nombre;

    @NotEmpty
    @Column(name = "med_descripcion")
    private String descripcion;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }


}
