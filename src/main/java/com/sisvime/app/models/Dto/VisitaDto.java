package com.sisvime.app.models.Dto;

import com.sisvime.app.models.entity.Personal;
import com.sisvime.app.models.entity.Vehiculo;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class VisitaDto implements Serializable {

    public int id;

    public Personal idper;

    public String time;

    public String desc;

    public String fecha;
    
    public String paren;

    public String tit;

    public String dist;

    public String zona;

    public String obs;

    public String idenf;

    public String nomenf;

    public String apeenf;

    public String pro_espenf;

    public String idtec;

    public String nomtec;

    public String apetec;

    public String esptec;

    public String hora;

    public Vehiculo idveh;

    public String idchf;

    public String nomchf;

    public String apechf;

    public String espchf;

    public Boolean isFree = true;

    public VisitaDto() {
    }

}
