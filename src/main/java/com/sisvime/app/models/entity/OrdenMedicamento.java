package com.sisvime.app.models.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
public class OrdenMedicamento implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int Id;
    @ManyToOne
    public Orden Orden;
    @ManyToOne
    public Medicamento Medicamento;
    public int Cantidad;
    public String ViaAdmision;
    public String Frecuencia;
}
