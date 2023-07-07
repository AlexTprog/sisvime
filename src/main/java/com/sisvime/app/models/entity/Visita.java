package com.sisvime.app.models.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "provisita")
@Getter
@Setter
public class Visita implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idvisita")
    private int id;

    @ManyToOne
    @JoinColumn(name = "per_id")
    private Personal idper;

    @Column(name = "prov_time")
    private String time;

    @Column(name = "prov_descripcion")
    private String desc;

    @Column(name = "prov_fecha")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fecha;

    @Column(name = "pro_parentesco")
    private String paren;

    @Column(name = "pro_titular")
    private String tit;

    @Column(name = "pro_distrito")
    private String dist;

    @Column(name = "pro_zona")
    private String zona;

    @Column(name = "pro_observacion")
    private String obs;

    @Column(name = "pro_nsaenf")
    private String idenf;

    @Column(name = "pro_nombreenf")
    private String nomenf;

    @Column(name = "pro_apellidoenf")
    private String apeenf;

    @Column(name = "pro_espenf")
    private String pro_espenf;

    @Column(name = "pro_nsatec")
    private String idtec;

    @Column(name = "pro_nombretec")
    private String nomtec;

    @Column(name = "pro_apellidotec")
    private String apetec;

    @Column(name = "pro_esptec")
    private String esptec;

    @Column(name = "pro_time")
    private String hora;

    @ManyToOne
    @JoinColumn(name = "veh_id")
    private Vehiculo idveh;

    @Column(name = "pro_nsachf")
    private String idchf;

    @Column(name = "pro_nombrechf")
    private String nomchf;

    @Column(name = "pro_apellidochf")
    private String apechf;

    @Column(name = "pro_espchf")
    private String espchf;

    @Column
    private Boolean isFree = true;

}
