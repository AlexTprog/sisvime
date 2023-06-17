package com.sisvime.app.models.Service;



import com.sisvime.app.models.entity.Cita;

import java.util.List;

public interface ICitaService {

    public List<Cita> listartodos();

    public void guardar(Cita cita);

    public Cita buscarporId(int id);

    public void eliminar(int id);
}
