package com.sisvime.app.service;

import com.sisvime.app.entity.Speciality;
import com.sisvime.app.repository.ISpecialityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class SpecialityService {
    @Autowired
    private ISpecialityRepository specialityRepository;

    public Speciality create(Speciality Speciality) {
        return specialityRepository.save(Speciality);
    }

    public Optional<Speciality> getById(Long id) {
        return specialityRepository.findById(id);
    }

    public ArrayList<Speciality> getAll() {
        return (ArrayList<Speciality>) specialityRepository.findAll();
    }
}
