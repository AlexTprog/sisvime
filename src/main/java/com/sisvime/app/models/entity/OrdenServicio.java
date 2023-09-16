package com.sisvime.app.models.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Setter
@Getter
public class OrdenServicio implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int Id;
    @ManyToOne
    public Orden Orden;
    public String Servicio;
    public String Prioridad;
    public String SolicitadoA;
    public Date FechaInicio;
}
