package com.sisvime.app.models.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "horario")
public class Horatrabajo implements Serializable {

    /**
     *
     */
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Personal getIdper() {
        return idper;
    }

    public void setIdper(Personal idper) {
        this.idper = idper;
    }

    public Hora getIdhora() {
        return idhora;
    }

    public void setIdhora(Hora idhora) {
        this.idhora = idhora;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "HoraTrabajo [id=" + id + ", idper=" + idper + ", idhora=" + idhora + ", fecha=" + fecha + ", getId()="
                + getId() + ", getIdper()=" + getIdper() + ", getIdhora()=" + getIdhora() + ", getFecha()=" + getFecha()
                + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
                + "]";
    }


}
