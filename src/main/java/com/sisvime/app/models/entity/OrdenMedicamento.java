package com.sisvime.app.models.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
public class OrdenMedicamento implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int Id;
    @ManyToOne
    public Orden Orden;
    public String Medicamento;
    public String Administrado;
    public int Cantidad;
    public String TipoCantidad;
    public String ViaAdmision;
    public String Frecuencia;
    public Date FechaInicio;
    public Date FechaFin;
}
