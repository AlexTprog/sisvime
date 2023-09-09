package com.sisvime.app.models.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.security.SecureRandom;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

@Entity
@Table(name = "pacientes")
@Getter
@Setter
public class Paciente implements Serializable {

    @Id
    @NotNull
    @Column(name = "pac_nsa")
    private Long id;

    @NotNull
    @Column(name = "pac_dni")
    private String dni;

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
    @Column(name = "pac_celular")
    private String celular;

    @NotNull
    @Column(name = "pac_telefonofijo")
    private String telefonofijo;

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

    @Column
    private Boolean estaHospitalizado = false;

    public Paciente() {
        this.id = generateNsa();
    }

    private Long generateNsa() {
        Random random = new Random();
        long num = random.nextInt(900000) + 100000;
        return num;
    }

    public int calculaEdad() {
        Date fechaNacimiento = this.fecha_nacimiento;

        Calendar fechaActual = Calendar.getInstance();

        Calendar fechaNac = Calendar.getInstance();
        fechaNac.setTime(fechaNacimiento);

        int edad = fechaActual.get(Calendar.YEAR) - fechaNac.get(Calendar.YEAR);

        if (fechaActual.get(Calendar.DAY_OF_YEAR) < fechaNac.get(Calendar.DAY_OF_YEAR)) {
            edad--;
        }

        return edad;
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
