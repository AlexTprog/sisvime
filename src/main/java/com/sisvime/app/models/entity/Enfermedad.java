package com.sisvime.app.models.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Entity
@Table(name = "enfermedades")
public class Enfermedad implements Serializable {

    private static final long serialVersionUID = 1L;


    @Id
    @Column(name = "enf_codigo")
    private int id;

    @NotEmpty
    @Column(name = "enf_nombre")
    private String nombre;

    @NotEmpty
    @Column(name = "enf_sintoma")
    private String sintoma;

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

    public String getSintoma() {
        return sintoma;
    }

    public void setSintoma(String sintoma) {
        this.sintoma = sintoma;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }


}
