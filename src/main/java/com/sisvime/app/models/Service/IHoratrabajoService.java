package com.sisvime.app.models.Service;

import com.sisvime.app.models.entity.Horatrabajo;

import java.util.List;


public interface IHoratrabajoService {

    public List<Horatrabajo> listartodos();

    public void guardar(Horatrabajo horatrabajo);

    public Horatrabajo buscarporId(int id);

    public void eliminar(int id);
}
