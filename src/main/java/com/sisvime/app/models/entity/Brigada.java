package com.sisvime.app.models.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "probrigada")
public class Brigada implements Serializable {

    /**
     *
     */
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

    @Column(name = "pro_fecha")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fecha;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Paciente getIdpac() {
        return idpac;
    }

    public void setIdpac(Paciente idpac) {
        this.idpac = idpac;
    }

    public String getHoraini() {
        return horaini;
    }

    public void setHoraini(String horaini) {
        this.horaini = horaini;
    }

    public String getHorafin() {
        return horafin;
    }

    public void setHorafin(String horafin) {
        this.horafin = horafin;
    }

    public String getTipobrigada() {
        return tipobrigada;
    }

    public void setTipobrigada(String tipobrigada) {
        this.tipobrigada = tipobrigada;
    }

    public String getZonabrigada() {
        return zonabrigada;
    }

    public void setZonabrigada(String zonabrigada) {
        this.zonabrigada = zonabrigada;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }


}