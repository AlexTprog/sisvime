package com.sisvime.app.models.Service.Imp;

import com.sisvime.app.models.Dao.ICitaDao;
import com.sisvime.app.models.Service.ICitaService;
import com.sisvime.app.models.entity.Cita;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CitaServiceImpl implements ICitaService {

    @Autowired
    private ICitaDao citadao;

    @Override
    public List<Cita> listartodos() {
        return (List<Cita>) citadao.findAll();
    }

    @Override
    public void guardar(Cita cita) {
        citadao.save(cita);

    }

    @Override
    public Cita buscarporId(int id) {
        return citadao.findById(id).orElse(null);
    }

    @Override
    public void eliminar(int id) {
        citadao.deleteById(id);

    }

    @Override
    public void softDelete(int id) {
        var cita = buscarporId(id);
        cita.setIsDelete(true);
        guardar(cita);
    }

}
