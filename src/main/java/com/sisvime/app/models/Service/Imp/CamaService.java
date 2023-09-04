package com.sisvime.app.models.Service.Imp;

import com.sisvime.app.models.Dao.ICamaRepository;
import com.sisvime.app.models.entity.Cama;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CamaService {

    @Autowired
    private ICamaRepository camaRepository;

    public List<Cama> getAll() {
        return camaRepository.findAll();
    }

    public Cama create(Cama cama) {
        return camaRepository.save(cama);
    }

    public Cama getById(Integer id) {
        return camaRepository.findById(id).orElse(null);
    }

    public void delete(Integer id) {
        camaRepository.deleteById(id);
    }
}
