package com.sisvime.app.service;

import com.sisvime.app.entity.roles.Rol;
import com.sisvime.app.repository.IRolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class RolService {
    @Autowired
    private IRolRepository rolRepository;

    public ArrayList<Rol> getAll() {
        return (ArrayList<Rol>) rolRepository.findAll();
    }

    public Rol create(Rol rol) {
        return rolRepository.save(rol);
    }

    public Optional<Rol> getById(Long id) {
        return rolRepository.findById(id);
    }


}
