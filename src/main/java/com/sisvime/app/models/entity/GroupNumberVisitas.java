package com.sisvime.app.models.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GroupNumberVisitas {
    private int visitas;
    private String nombreMedico;
    private String apellidoMedico;
    private String nombreEnfermera;
    private String apellidoEnfermera;
    private String nombreTecnico;
    private String apellidoTecnico;
}
