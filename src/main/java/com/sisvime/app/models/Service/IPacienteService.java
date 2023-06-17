package com.sisvime.app.models.Service;


import com.sisvime.app.models.entity.Paciente;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IPacienteService {

    public List<Paciente> listartodos();

    public void guardar(Paciente paciente);

    public Paciente buscarporId(Long id);

    public void eliminar(Long id);

    public List<Paciente> findByNombre(String term);
}
