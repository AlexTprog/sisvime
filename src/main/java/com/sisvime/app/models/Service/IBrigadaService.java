package com.sisvime.app.models.Service;


import com.sisvime.app.models.entity.Brigada;
import com.sisvime.app.models.entity.DistritoParentesco;
import com.sisvime.app.models.entity.TitularParentesco;

import java.util.List;

public interface IBrigadaService {

    public List<Brigada> listartodos();

    public void guardar(Brigada brigada);

    public Brigada buscarporId(int id);

    public void eliminar(int id);

    public List<DistritoParentesco> distritoParentesco(int month, int year);

    public List<DistritoParentesco> distritoParentescoActual();

    public List<TitularParentesco> titularParentesco(int month, int year);

    public List<TitularParentesco> titularParentescoActual();

}
