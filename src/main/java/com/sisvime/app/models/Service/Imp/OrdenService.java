package com.sisvime.app.models.Service.Imp;

import com.sisvime.app.models.Dao.IOrdenRepository;
import com.sisvime.app.models.entity.Orden;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdenService {
    @Autowired
    private IOrdenRepository ordenRepository;

    public List<Orden> getAll() {
        return ordenRepository.findAll();
    }

    public Orden create(Orden orden) {
        return ordenRepository.save(orden);
    }

    public Orden findById(Integer id) {
        return ordenRepository.findById(id).orElse(null);
    }

    public void delete(Integer id) {
        ordenRepository.deleteById(id);
    }
}
