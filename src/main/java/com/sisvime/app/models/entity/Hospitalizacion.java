package com.sisvime.app.models.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
public class Hospitalizacion implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int Id;
    @ManyToOne
    public Paciente Paciente;
    @ManyToOne
    public Personal Personal;
    public String TipoPaciente;
    public String TipoAtencion;
    @ManyToOne
    public Cama Cama;
    public Date FechaAdmision;
    public Date FechaAlta;
    @ManyToOne
    public Orden Orden;
}
