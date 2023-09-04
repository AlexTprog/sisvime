package com.sisvime.app.models.Service.Imp;

import com.sisvime.app.models.Dao.IOrdenDietaRepository;
import com.sisvime.app.models.entity.OrdenDieta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdenDietaService {
    @Autowired
    private IOrdenDietaRepository ordenDietaRepository;

    public List<OrdenDieta> getAll() {
        return ordenDietaRepository.findAll();
    }

    public OrdenDieta create(OrdenDieta ordenDieta) {
        return ordenDietaRepository.save(ordenDieta);
    }

    public OrdenDieta getById(Integer id) {
        return ordenDietaRepository.findById(id).orElse(null);
    }

    public void delete(Integer id) {
        ordenDietaRepository.deleteById(id);
    }
}
