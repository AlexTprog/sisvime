package com.sisvime.app.models.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "pacientes")
public class Paciente implements Serializable {

    @NotNull
    @Id
    @Column(name = "pac_nsa")
    private Long id;

    private static final long serialVersionUID = 1L;


    @NotNull
    @Column(name = "pac_dni")
    private Long dni;

    @NotEmpty
    @Column(name = "pac_nombre")
    private String nombre;

    @NotEmpty
    @Column(name = "pac_apellido_pa")
    private String apellido_pa;

    @NotEmpty
    @Column(name = "pac_apellido_ma")
    private String apellidoma;

    @NotEmpty
    @Column(name = "pac_parentesco")
    private String parentesco;

    @NotNull
    @Column(name = "pac_fecha_nacimiento")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fecha_nacimiento;

    @NotEmpty
    @Column(name = "pac_direccion")
    private String direccion;

    @NotEmpty
    @Email(message = "Correo con formato incorrecto")
    @Column(name = "pac_correo")
    private String correo;

    @NotNull
    @Min(100000000)
    @Max(999999999)
    @Column(name = "pac_celular")
    private Long celular;

    @NotNull
    @Min(1000000)
    @Max(9999999)
    @Column(name = "pac_telefonofijo")
    private Long telefonofijo;


    @Column(name = "pac_foto")
    private String foto = "iconouser.png";

    @Column(name = "pac_sexo")
    private String sexo;

    @Column(name = "pac_estadocivil")
    private String estadocivil;

    @Column(name = "pac_distrito")
    private String distrito;

    @Column(name = "pac_titular")
    private String titular;

    @Column(name = "pac_peso")
    private String peso;

    @Column(name = "pac_masa")
    private String masa;

    @Column(name = "pac_altura")
    private String altura;

    @Column(name = "pac_tiposangre")
    private String sangre;

    @Column(name = "pac_alergia")
    private String alergia;

    @Column(name = "pac_tipoalergia")
    private String tipalergia;

    @Column(name = "pac_alergiadesc")
    private String descalergia;

    @Column(name = "pac_enfermedad")
    private String enfermedad;

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

    public String getApellido_pa() {
        return apellido_pa;
    }

    public void setApellido_pa(String apellido_pa) {
        this.apellido_pa = apellido_pa;
    }

    public String getApellidoma() {
        return apellidoma;
    }

    public void setApellidoma(String apellidoma) {
        this.apellidoma = apellidoma;
    }

    public String getParentesco() {
        return parentesco;
    }

    public void setParentesco(String parentesco) {
        this.parentesco = parentesco;
    }

    public Date getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(Date fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
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

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getEstadocivil() {
        return estadocivil;
    }

    public void setEstadocivil(String estadocivil) {
        this.estadocivil = estadocivil;
    }

    public String getDistrito() {
        return distrito;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public String getMasa() {
        return masa;
    }

    public void setMasa(String masa) {
        this.masa = masa;
    }

    public String getAltura() {
        return altura;
    }

    public void setAltura(String altura) {
        this.altura = altura;
    }

    public String getSangre() {
        return sangre;
    }

    public void setSangre(String sangre) {
        this.sangre = sangre;
    }

    public String getAlergia() {
        return alergia;
    }

    public void setAlergia(String alergia) {
        this.alergia = alergia;
    }

    public String getTipalergia() {
        return tipalergia;
    }

    public void setTipalergia(String tipalergia) {
        this.tipalergia = tipalergia;
    }

    public String getDescalergia() {
        return descalergia;
    }

    public void setDescalergia(String descalergia) {
        this.descalergia = descalergia;
    }

    public String getEnfermedad() {
        return enfermedad;
    }

    public void setEnfermedad(String enfermedad) {
        this.enfermedad = enfermedad;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    @Override
    public String toString() {
        return "Paciente [id=" + id + ", dni=" + dni + ", nombre=" + nombre + ", apellido_pa=" + apellido_pa
                + ", apellidoma=" + apellidoma + ", parentesco=" + parentesco + ", fecha_nacimiento=" + fecha_nacimiento
                + ", direccion=" + direccion + ", correo=" + correo + ", celular=" + celular + ", telefonofijo="
                + telefonofijo + ", foto=" + foto + ", sexo=" + sexo + ", estadocivil=" + estadocivil + ", distrito="
                + distrito + ", titular=" + titular + ", peso=" + peso + ", masa=" + masa + ", altura=" + altura
                + ", sangre=" + sangre + ", alergia=" + alergia + ", tipalergia=" + tipalergia + ", descalergia="
                + descalergia + ", enfermedad=" + enfermedad + ", getId()=" + getId() + ", getDni()=" + getDni()
                + ", getNombre()=" + getNombre() + ", getApellido_pa()=" + getApellido_pa() + ", getApellidoma()="
                + getApellidoma() + ", getParentesco()=" + getParentesco() + ", getFecha_nacimiento()="
                + getFecha_nacimiento() + ", getDireccion()=" + getDireccion() + ", getCorreo()=" + getCorreo()
                + ", getCelular()=" + getCelular() + ", getTelefonofijo()=" + getTelefonofijo() + ", getFoto()="
                + getFoto() + ", getSexo()=" + getSexo() + ", getEstadocivil()=" + getEstadocivil() + ", getDistrito()="
                + getDistrito() + ", getTitular()=" + getTitular() + ", getPeso()=" + getPeso() + ", getMasa()="
                + getMasa() + ", getAltura()=" + getAltura() + ", getSangre()=" + getSangre() + ", getAlergia()="
                + getAlergia() + ", getTipalergia()=" + getTipalergia() + ", getDescalergia()=" + getDescalergia()
                + ", getEnfermedad()=" + getEnfermedad() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
                + ", toString()=" + super.toString() + "]";
    }


}
