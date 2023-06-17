package com.sisvime.app.models.Service.Imp;

import com.sisvime.app.models.Dao.IPersonaDao;
import com.sisvime.app.models.Service.IPersonaService;
import com.sisvime.app.models.entity.Personal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonaServiceImpl implements IPersonaService {

    @Autowired
    private IPersonaDao personadao;

    @Override
    public List<Personal> listartodos() {
        return (List<Personal>) personadao.findAll();
    }

    @Override
    public void guardar(Personal personal) {
        personadao.save(personal);

    }

    @Override
    public Personal buscarporId(Long id) {

        return personadao.findById(id).orElse(null);
    }

    @Override
    public void eliminar(Long id) {

        personadao.deleteById(id);
    }


}
