package com.sisvime.app.service;

import com.sisvime.app.entity.Speciality;
import com.sisvime.app.repository.ISpecialityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public Speciality getById(Long id) {
        return specialityRepository.findById(id).orElse(null);
    }

    public ArrayList<Speciality> getAll() {
        return (ArrayList<Speciality>) specialityRepository.findAll();
    }

    public Page<Speciality> getAll(Pageable pageable){
        return specialityRepository.findAll(pageable);
    }

    public void delete(Long id){
        specialityRepository.deleteById(id);
    }
}
