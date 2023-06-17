package com.sisvime.app.models.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "provisita")
public class Visita implements Serializable {

    /**
     *
     */
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Personal getIdper() {
        return idper;
    }

    public void setIdper(Personal idper) {
        this.idper = idper;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getParen() {
        return paren;
    }

    public void setParen(String paren) {
        this.paren = paren;
    }

    public String getTit() {
        return tit;
    }

    public void setTit(String tit) {
        this.tit = tit;
    }

    public String getDist() {
        return dist;
    }

    public void setDist(String dist) {
        this.dist = dist;
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public String getIdenf() {
        return idenf;
    }

    public void setIdenf(String idenf) {
        this.idenf = idenf;
    }

    public String getNomenf() {
        return nomenf;
    }

    public void setNomenf(String nomenf) {
        this.nomenf = nomenf;
    }

    public String getApeenf() {
        return apeenf;
    }

    public void setApeenf(String apeenf) {
        this.apeenf = apeenf;
    }

    public String getPro_espenf() {
        return pro_espenf;
    }

    public void setPro_espenf(String pro_espenf) {
        this.pro_espenf = pro_espenf;
    }

    public String getIdtec() {
        return idtec;
    }

    public void setIdtec(String idtec) {
        this.idtec = idtec;
    }

    public String getNomtec() {
        return nomtec;
    }

    public void setNomtec(String nomtec) {
        this.nomtec = nomtec;
    }

    public String getApetec() {
        return apetec;
    }

    public void setApetec(String apetec) {
        this.apetec = apetec;
    }

    public String getEsptec() {
        return esptec;
    }

    public void setEsptec(String esptec) {
        this.esptec = esptec;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public Vehiculo getIdveh() {
        return idveh;
    }

    public void setIdveh(Vehiculo idveh) {
        this.idveh = idveh;
    }

    public String getIdchf() {
        return idchf;
    }

    public void setIdchf(String idchf) {
        this.idchf = idchf;
    }

    public String getNomchf() {
        return nomchf;
    }

    public void setNomchf(String nomchf) {
        this.nomchf = nomchf;
    }

    public String getApechf() {
        return apechf;
    }

    public void setApechf(String apechf) {
        this.apechf = apechf;
    }

    public String getEspchf() {
        return espchf;
    }

    public void setEspchf(String espchf) {
        this.espchf = espchf;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }


}
