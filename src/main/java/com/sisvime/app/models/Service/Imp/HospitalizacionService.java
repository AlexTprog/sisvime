package com.sisvime.app.models.Service.Imp;

import com.sisvime.app.models.Dao.IHospitalizacionRepostiory;
import com.sisvime.app.models.entity.Hospitalizacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HospitalizacionService {
    @Autowired
    private IHospitalizacionRepostiory hospitalizacionRepostiory;

    public List<Hospitalizacion> getAll(){
        return hospitalizacionRepostiory.findAll();
    }

    public Hospitalizacion create(Hospitalizacion hospitalizacion){
        return hospitalizacionRepostiory.save(hospitalizacion);
    }

    public Hospitalizacion getById(Integer id){
        return hospitalizacionRepostiory.findById(id).orElse(null);
    }

    public void delete(Integer id){
        hospitalizacionRepostiory.deleteById(id);
    }
}
