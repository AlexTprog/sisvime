package com.sisvime.app.models.Service.Imp;


import com.sisvime.app.models.Dao.IPacienteDao;
import com.sisvime.app.models.Service.IPacienteService;
import com.sisvime.app.models.entity.Paciente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class PacienteServiceImpl implements IPacienteService {

    @Autowired
    private IPacienteDao pacientedao;

    @Override
    public List<Paciente> listartodos() {

        return (List<Paciente>) pacientedao.findAll();

    }

    @Override
    public void guardar(Paciente paciente) {
        pacientedao.save(paciente);

    }

    @Override
    public Paciente buscarporId(Long id) {

        return pacientedao.findById(id).orElse(null);
    }

    @Override
    public void eliminar(Long id) {
        pacientedao.deleteById(id);

    }

    @Override
    @Transactional(readOnly = true)
    public List<Paciente> findByNombre(String term) {
        return pacientedao.findByNombre(term);
    }

}
