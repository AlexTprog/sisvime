package com.sisvime.app.models.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "vehiculos")
public class Vehiculo implements Serializable {


    private static final long serialVersionUID = 1L;

    @NotNull
    @Id
    @Column(name = "veh_placa")
    private int id;

    @NotEmpty
    @Column(name = "veh_marca")
    private String marca;

    @NotEmpty
    @Column(name = "veh_model")
    private String modelo;

    @NotEmpty
    @Column(name = "veh_color")
    private String color;

    @NotNull
    @Column(name = "veh_motor")
    private Long motor;

    @NotEmpty
    @Column(name = "veh_combustible")
    private String combustible;

    @NotNull
    @Column(name = "veh_fabricacion")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fabrica;

    @NotNull
    @Column(name = "veh_ejes")
    private Long ejes;

    @Column(name = "veh_estatus")
    private int estatus;

    @Column(name = "veh_foto")
    private String foto = "iconouser.png";

    @Column(name = "veh_nomplaca")
    private String placa;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Long getMotor() {
        return motor;
    }

    public void setMotor(Long motor) {
        this.motor = motor;
    }

    public String getCombustible() {
        return combustible;
    }

    public void setCombustible(String combustible) {
        this.combustible = combustible;
    }

    public Date getFabrica() {
        return fabrica;
    }

    public void setFabrica(Date fabrica) {
        this.fabrica = fabrica;
    }

    public Long getEjes() {
        return ejes;
    }

    public void setEjes(Long ejes) {
        this.ejes = ejes;
    }

    public int getEstatus() {
        return estatus;
    }

    public void setEstatus(int estatus) {
        this.estatus = estatus;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    @Override
    public String toString() {
        return "Vehiculo [id=" + id + ", marca=" + marca + ", modelo=" + modelo + ", color=" + color + ", motor="
                + motor + ", combustible=" + combustible + ", fabrica=" + fabrica + ", ejes=" + ejes + ", estatus="
                + estatus + ", foto=" + foto + ", placa=" + placa + ", getId()=" + getId() + ", getMarca()="
                + getMarca() + ", getModelo()=" + getModelo() + ", getColor()=" + getColor() + ", getMotor()="
                + getMotor() + ", getCombustible()=" + getCombustible() + ", getFabrica()=" + getFabrica()
                + ", getEjes()=" + getEjes() + ", getEstatus()=" + getEstatus() + ", getFoto()=" + getFoto()
                + ", getPlaca()=" + getPlaca() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
                + ", toString()=" + super.toString() + "]";
    }


}
