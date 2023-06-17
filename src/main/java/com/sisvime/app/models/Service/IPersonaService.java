package com.sisvime.app.models.Service;

import com.sisvime.app.models.entity.Personal;

import java.util.List;

public interface IPersonaService {

    public List<Personal> listartodos();

    public void guardar(Personal personal);

    public Personal buscarporId(Long id);

    public void eliminar(Long id);

}
