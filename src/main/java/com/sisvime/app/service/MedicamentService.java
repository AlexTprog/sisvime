package com.sisvime.app.service;


import com.sisvime.app.entity.Medicament;
import com.sisvime.app.repository.IMedicamentRepostiory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MedicamentService {
    @Autowired
    private IMedicamentRepostiory medicamentRepostiory;

    public ArrayList<Medicament> getAll() {
        return (ArrayList<Medicament>) medicamentRepostiory.findAll();
    }

    public Page<Medicament> getAll(Pageable pageable) {
        return medicamentRepostiory.findAll(pageable);
    }

    public Medicament create(Medicament medicament) {
        return medicamentRepostiory.save(medicament);
    }

    public Medicament getById(Long id) {
        return medicamentRepostiory.findById(id).orElse(null);
    }

    public void delete(Long id) {
        medicamentRepostiory.deleteById(id);
    }


}
