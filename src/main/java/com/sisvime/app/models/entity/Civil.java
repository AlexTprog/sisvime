package com.sisvime.app.models.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "civiles")
public class Civil implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "est_id")
    private long id;

    @Column(name = "est_nombre")
    private String nombre;

    public long getId() {
        return id;
    }

    public void setId(long id) {
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
