package com.sisvime.app.models.Dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class HospitalizacionDto {
    public int Cama;
    public long Personal;
    public long Paciente;
    public Date FechaAdmision;
}
