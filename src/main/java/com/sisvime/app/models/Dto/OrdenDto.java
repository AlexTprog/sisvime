package com.sisvime.app.models.Dto;

import java.util.Date;
import java.util.List;

public class OrdenDto {
    public String indicaciones;
    public Date fechaInicio;
    public List<OrdenDietaDto> dietas;

    public List<OrdenMedicamentoDto> medicamentos;
    public List<OrdenServiciosDto> servicios;
    public int idHospitalizacion;
}
