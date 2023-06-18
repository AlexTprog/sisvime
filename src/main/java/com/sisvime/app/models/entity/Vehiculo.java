package com.sisvime.app.models.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "vehiculos")
@Getter
@Setter
public class Vehiculo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    private int ejes;

    @Column(name = "veh_estatus")
    private int estatus;

    @Column(name = "veh_foto")
    private String foto = "iconouser.png";

    @Column(name = "veh_nomplaca")
    private String placa;

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
