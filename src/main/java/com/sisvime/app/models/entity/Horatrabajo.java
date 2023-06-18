package com.sisvime.app.models.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "horario")
@Getter
@Setter
public class Horatrabajo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idhorario")
    private int id;

    @ManyToOne
    @JoinColumn(name = "per_id")
    private Personal idper;

    @ManyToOne
    @JoinColumn(name = "hora_id")
    private Hora idhora;

    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fecha;


    @Override
    public String toString() {
        return "HoraTrabajo [id=" + id + ", idper=" + idper + ", idhora=" + idhora + ", fecha=" + fecha + ", getId()="
                + getId() + ", getIdper()=" + getIdper() + ", getIdhora()=" + getIdhora() + ", getFecha()=" + getFecha()
                + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
                + "]";
    }


}
