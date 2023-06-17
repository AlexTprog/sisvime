package com.sisvime.app.models.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "citas")
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Paciente getPac() {
        return pac;
    }

    public void setPac(Paciente pac) {
        this.pac = pac;
    }

    public String getEsp() {
        return esp;
    }

    public void setEsp(String esp) {
        this.esp = esp;
    }

    public String getMed() {
        return med;
    }

    public void setMed(String med) {
        this.med = med;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Hora getIdhora() {
        return idhora;
    }

    public void setIdhora(Hora idhora) {
        this.idhora = idhora;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Enfermedad getIdenfermedad() {
        return idenfermedad;
    }

    public void setIdenfermedad(Enfermedad idenfermedad) {
        this.idenfermedad = idenfermedad;
    }

    public Medicamento getIdmedicamento() {
        return idmedicamento;
    }

    public void setIdmedicamento(Medicamento idmedicamento) {
        this.idmedicamento = idmedicamento;
    }

    public String getDiag() {
        return diag;
    }

    public void setDiag(String diag) {
        this.diag = diag;
    }

    public String getReceta() {
        return receta;
    }

    public void setReceta(String receta) {
        this.receta = receta;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

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
