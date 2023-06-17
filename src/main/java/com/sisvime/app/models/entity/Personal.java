package com.sisvime.app.models.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "personales")
public class Personal implements Serializable {

    @NotNull
    @Id
    @Column(name = "per_id")
    private Long id;

    private static final long serialVersionUID = 1L;

    @NotNull
    @Min(10000000)
    @Max(99999999)
    @Column(name = "per_dni")
    private Long dni;

    @NotEmpty
    @Column(name = "per_nombre")
    private String nombre;

    @NotEmpty
    @Column(name = "per_apellidopat")
    private String apellidopat;

    @NotEmpty
    @Column(name = "per_apellidomat")
    private String apellidomat;

    @NotEmpty
    @Column(name = "per_sexos")
    private String sexo;

    @NotNull
    @Column(name = "per_fechanac")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechanacimiento;

    @NotEmpty
    @Column(name = "per_estadocivil")
    private String estadocivil;

    @NotEmpty
    @Email(message = "Correo con formato incorrecto")
    @Column(name = "per_correo")
    private String correo;

    @NotNull
    @Min(100000000)
    @Max(999999999)
    @Column(name = "per_celular")
    private Long celular;

    @NotNull
    @Min(1000000)
    @Max(9999999)
    @Column(name = "per_telefonofijo")
    private Long telefonofijo;

    @NotEmpty
    @Column(name = "per_direccion")
    private String direccion;

    @ManyToOne
    @JoinColumn(name = "esp_id")
    private Especialidad espec;

    @Column(name = "per_foto")
    private String foto = "iconouser.png";


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public Long getDni() {
        return dni;
    }


    public void setDni(Long dni) {
        this.dni = dni;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidopat() {
        return apellidopat;
    }

    public void setApellidopat(String apellidopat) {
        this.apellidopat = apellidopat;
    }

    public String getApellidomat() {
        return apellidomat;
    }

    public void setApellidomat(String apellidomat) {
        this.apellidomat = apellidomat;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Date getFechanacimiento() {
        return fechanacimiento;
    }

    public void setFechanacimiento(Date fechanacimiento) {
        this.fechanacimiento = fechanacimiento;
    }

    public String getEstadocivil() {
        return estadocivil;
    }

    public void setEstadocivil(String estadocivil) {
        this.estadocivil = estadocivil;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Long getCelular() {
        return celular;
    }

    public void setCelular(Long celular) {
        this.celular = celular;
    }

    public Long getTelefonofijo() {
        return telefonofijo;
    }

    public void setTelefonofijo(Long telefonofijo) {
        this.telefonofijo = telefonofijo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Especialidad getEspec() {
        return espec;
    }

    public void setEspec(Especialidad espec) {
        this.espec = espec;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }


    @Override
    public String toString() {
        return "Personal [id=" + id + ", dni=" + dni + ", nombre=" + nombre + ", apellidopat=" + apellidopat
                + ", apellidomat=" + apellidomat + ", sexo=" + sexo + ", fechanacimiento=" + fechanacimiento
                + ", estadocivil=" + estadocivil + ", correo=" + correo + ", celular=" + celular + ", telefonofijo="
                + telefonofijo + ", direccion=" + direccion + ", espec=" + espec + ", foto=" + foto + "]";
    }


}
