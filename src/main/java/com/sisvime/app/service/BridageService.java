package com.sisvime.app.service;

import com.sisvime.app.entity.Brigade;
import com.sisvime.app.repository.IBrigadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class BridageService {

    @Autowired
    private IBrigadeRepository brigadeRepository;

    public ArrayList<Brigade> getAll() {
        return (ArrayList<Brigade>) brigadeRepository.findAll();
    }

    public Brigade create(Brigade brigade) {
        return brigadeRepository.save(brigade);
    }

    public Brigade getById(Long id) {
        return brigadeRepository.findById(id).orElse(null);
    }

    public void delete(Long id) {
        brigadeRepository.deleteById(id);
    }
}
