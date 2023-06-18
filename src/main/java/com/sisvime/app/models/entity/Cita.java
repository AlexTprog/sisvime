package com.sisvime.app.models.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "citas")
@Getter
@Setter
public class Cita implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cit_codigo")
    private int id;

    @ManyToOne
    @JoinColumn(name = "id_paciente")
    private Paciente pac;

    @Column(name = "cit_esp")
    private String esp;

    @Column(name = "cit_med")
    private String med;


    @Column(name = "cit_fecha")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fecha;


    @ManyToOne
    @JoinColumn(name = "hora_id")
    private Hora idhora;

    @Column(name = "cit_estado")
    private String estado;

    @Column(name = "id_enfermedad")
    private Enfermedad idenfermedad;

    @Column(name = "id_medicamento")
    private Medicamento idmedicamento;

    @Column(name = "cit_diagnostico")
    private String diag;

    @Column(name = "cit_receta")
    private String receta;


    @Override
    public String toString() {
        return "Cita [id=" + id + ", pac=" + pac + ", esp=" + esp + ", med=" + med + ", fecha=" + fecha + ", idhora="
                + idhora + ", estado=" + estado + ", idenfermedad=" + idenfermedad + ", idmedicamento=" + idmedicamento
                + ", diag=" + diag + ", receta=" + receta + ", getId()=" + getId() + ", getPac()=" + getPac()
                + ", getEsp()=" + getEsp() + ", getMed()=" + getMed() + ", getFecha()=" + getFecha() + ", getIdhora()="
                + getIdhora() + ", getEstado()=" + getEstado() + ", getIdenfermedad()=" + getIdenfermedad()
                + ", getIdmedicamento()=" + getIdmedicamento() + ", getDiag()=" + getDiag() + ", getReceta()="
                + getReceta() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
                + super.toString() + "]";
    }


}
