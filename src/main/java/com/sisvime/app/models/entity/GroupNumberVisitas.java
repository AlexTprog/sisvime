package com.sisvime.app.models.entity;

public class GroupNumberVisitas {
    private int visitas;
    private String nombreMedico;
    private String apellidoMedico;

    private String nombreEnfermera;
    private String apellidoEnfermera;

    private String nombreTecnico;
    private String apellidoTecnico;


    public int getNumero() {
        return visitas;
    }

    public void setNumero(int visitas) {
        this.visitas = visitas;
    }

    public String getNombreMedico() {
        return nombreMedico;
    }

    public void setNombreMedico(String nombreMedico) {
        this.nombreMedico = nombreMedico;
    }

    public String getApellidoMedico() {
        return apellidoMedico;
    }

    public void setApellidoMedico(String apellidoMedico) {
        this.apellidoMedico = apellidoMedico;
    }

    public String getNombreEnfermera() {
        return nombreEnfermera;
    }

    public void setNombreEnfermera(String nombreEnfermera) {
        this.nombreEnfermera = nombreEnfermera;
    }

    public String getApellidoEnfermera() {
        return apellidoEnfermera;
    }

    public void setApellidoEnfermera(String apellidoEnfermera) {
        this.apellidoEnfermera = apellidoEnfermera;
    }

    public String getNombreTecnico() {
        return nombreTecnico;
    }

    public void setNombreTecnico(String nombreTecnico) {
        this.nombreTecnico = nombreTecnico;
    }

    public String getApellidoTecnico() {
        return apellidoTecnico;
    }

    public void setApellidoTecnico(String apellidoTecnico) {
        this.apellidoTecnico = apellidoTecnico;
    }


}
