package com.sisvime.app.models.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.Random;

@Entity
@Table(name = "personal")
@Getter
@Setter
public class Personal implements Serializable {
    @Id
    @Column(name = "per_id")
    private Long id;

    @NotNull
    @Column(name = "per_dni")
    private String dni;

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
    @Column(name = "per_celular")
    private String celular;

    @NotNull
    @Column(name = "per_telefonofijo")
    private String telefonofijo;

    @NotEmpty
    @Column(name = "per_direccion")
    private String direccion;

    @ManyToOne
    @JoinColumn(name = "esp_id")
    private Especialidad espec;

    @Column(name = "per_foto")
    private String foto = "iconouser.png";

    public Personal() {
    }

    public Personal(String dni, String nombre, String apellidopat, String apellidomat, String sexo,
            Date fechanacimiento, String correo, String celular, Especialidad espec) {
        this.id = generateNsa();
        this.dni = dni;
        this.nombre = nombre;
        this.apellidopat = apellidopat;
        this.apellidomat = apellidomat;
        this.sexo = sexo;
        this.fechanacimiento = fechanacimiento;
        this.correo = correo;
        this.celular = celular;
        this.espec = espec;
    }

    private Long generateNsa() {
        Random random = new Random();
        long num = random.nextInt(900000) + 100000;
        return num;
    }

    @Override
    public String toString() {
        return "Personal [id=" + id + ", dni=" + dni + ", nombre=" + nombre + ", apellidopat=" + apellidopat
                + ", apellidomat=" + apellidomat + ", sexo=" + sexo + ", fechanacimiento=" + fechanacimiento
                + ", estadocivil=" + estadocivil + ", correo=" + correo + ", celular=" + celular + ", telefonofijo="
                + telefonofijo + ", direccion=" + direccion + ", espec=" + espec + ", foto=" + foto + "]";
    }

}
