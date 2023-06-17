package com.sisvime.app.models.Service.Imp;

import com.sisvime.app.models.Dao.IHoratrabajoDao;
import com.sisvime.app.models.Service.IHoratrabajoService;
import com.sisvime.app.models.entity.Horatrabajo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IHoratrabajoServiceImpl implements IHoratrabajoService {

    @Autowired
    private IHoratrabajoDao horatrabajodao;


    @Override
    public List<Horatrabajo> listartodos() {
        return (List<Horatrabajo>) horatrabajodao.findAll();
    }

    @Override
    public void guardar(Horatrabajo horatrabajo) {
        horatrabajodao.save(horatrabajo);

    }

    @Override
    public Horatrabajo buscarporId(int id) {
        return horatrabajodao.findById(id).orElse(null);
    }

    @Override
    public void eliminar(int id) {
        horatrabajodao.deleteById(id);
    }

}
