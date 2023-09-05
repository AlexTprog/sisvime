package com.sisvime.app.models.Service.Imp;

import com.sisvime.app.models.Dao.IOrdenServicioRepository;
import com.sisvime.app.models.entity.OrdenServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdenServicioService {
    @Autowired
    private IOrdenServicioRepository ordenServicioRepository;

    public List<OrdenServicio> getAll(){
        return ordenServicioRepository.findAll();
    }

    public OrdenServicio create(OrdenServicio ordenServicio){
        return ordenServicioRepository.save(ordenServicio);
    }

    public OrdenServicio findById(Integer id){
        return ordenServicioRepository.findById(id).orElse(null);
    }

    public void delete(Integer id){
        ordenServicioRepository.deleteById(id);
    }
}
