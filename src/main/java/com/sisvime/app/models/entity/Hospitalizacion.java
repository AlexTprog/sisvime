package com.sisvime.app.models.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Random;

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
    public String TipoPaciente = "Hospitalizacion";
    public String TipoAtencion = "Hospitalizacion";
    @ManyToOne
    public Cama Cama;
    public Date FechaAdmision;
    public Date FechaAlta;
    @ManyToOne
    public Orden Orden;

    public Hospitalizacion() {
        this.Id = generateNsa();
    }

    private int generateNsa() {
        Random random = new Random();
        return random.nextInt(900000) + 100000;
    }

    public String GetNombreCompletoPaciente() {
        return this.Paciente.getNombre() + ' ' + this.Paciente.getApellido_pa() + ' ' + this.Paciente.getApellidoma();
    }

    public String GetNombreCompletoDoctor() {
        return this.Personal.getNombre() + ' ' + this.Personal.getApellidopat() + ' ' + this.Personal.getApellidomat();
    }
}
