package com.sisvime.app.service;


import com.sisvime.app.entity.Nurse;
import com.sisvime.app.repository.INurseRepostiory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Optional;

public class NurseService {

    @Autowired
    private INurseRepostiory nurseRepostiory;

    public ArrayList<Nurse> getAll() {
        return (ArrayList<Nurse>) nurseRepostiory.findAll();
    }

    public Optional<Nurse> getById(Long id) {
        return nurseRepostiory.findById(id);
    }

    public Nurse create(Nurse Nurse) {
        return nurseRepostiory.save(Nurse);
    }
}
