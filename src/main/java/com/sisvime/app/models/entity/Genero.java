package com.sisvime.app.models.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "generos")
public class Genero implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "gen_id")
    private Long id;

    @Column(name = "gen_nombre")
    private String nombre;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }


}
