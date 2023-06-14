package com.sisvime.app.service;


import com.sisvime.app.entity.Medicament;
import com.sisvime.app.repository.IMedicamentRepostiory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Optional;

public class MedicamentService {
    @Autowired
    private IMedicamentRepostiory medicamentRepostiory;

    public ArrayList<Medicament> getAll() {

        return (ArrayList<Medicament>) medicamentRepostiory.findAll();
    }

    public Medicament create(Medicament medicament) {
        return medicamentRepostiory.save(medicament);
    }

    public Optional<Medicament> getById(Long id) {
        return medicamentRepostiory.findById(id);
    }

}
