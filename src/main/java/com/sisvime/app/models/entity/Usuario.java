package com.sisvime.app.models.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "usuarios")
@Getter
@Setter
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nombre;
    private String apellido;
    private String email;
    private String username;
    private String password;
    private Integer estatus;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "usuario_perfil",
            joinColumns = @JoinColumn(name = "id_Usuario"),
            inverseJoinColumns = @JoinColumn(name = "id_Perfil"))
    private List<Perfil> perfiles;

    public void agregar(Perfil tempPerfil) {
        if (perfiles == null) {
            perfiles = new LinkedList<Perfil>();
        }
        perfiles.add(tempPerfil);
    }

    public Usuario() {
        this.estatus = 1;//ACTIVADO POR DEFECTO
    }

    @Override
    public String toString() {
        return "Usuario [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", email=" + email
                + ", username=" + username + ", password=" + password + ", estatus=" + estatus + ", perfiles="
                + perfiles + "]";
    }


}
