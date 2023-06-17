package com.sisvime.app.models.Service;



import com.sisvime.app.models.entity.Hora;

import java.util.List;

public interface IHoraService {

    List<Hora> listaHora();

    Hora Get(int id);
}
