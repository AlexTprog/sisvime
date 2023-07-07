package com.sisvime.app.models.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "probrigada")
@Getter
@Setter
public class Brigada implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pro_id_brigada")
    private int id;

    @ManyToOne
    @JoinColumn(name = "pac_id")
    private Paciente idpac;

    @Column(name = "pro_horaini")
    private String horaini;

    @Column(name = "pro_horafin")
    private String horafin;

    @Column(name = "pro_brigada")
    private String tipobrigada;

    @Column(name = "pro_zonabrigada")
    private String zonabrigada;

    @Column(name = "pro_obs")
    private String obs;

    @Column
    private String observacion;

    @Column(name = "pro_fecha")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fecha;

}