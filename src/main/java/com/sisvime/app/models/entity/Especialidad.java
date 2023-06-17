package com.sisvime.app.models.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "especialidades")
public class Especialidad implements Serializable {

    @NotNull
    @Id
    @Column(name = "esp_id")
    private Long id;

    @NotEmpty
    @Column(name = "esp_especialidad")
    private String nomespecialidad;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomespecialidad() {
        return nomespecialidad;
    }

    public void setNomespecialidad(String nomespecialidad) {
        this.nomespecialidad = nomespecialidad;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }


}
