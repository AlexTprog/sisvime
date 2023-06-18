package com.sisvime.app.models.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalTime;

@Entity
@Table(name = "hora")
@Getter
@Setter
public class Hora implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idhora")
    private int id;

    @Column(name = "hora")
    private LocalTime hora;

    public Hora() {
    }

    public Hora(int id, LocalTime hora) {
        this.id = id;
        this.hora = hora;
    }

    public Hora(LocalTime hora) {
        this.hora = hora;
    }
}
