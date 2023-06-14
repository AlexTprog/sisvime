package com.sisvime.app.service;

import com.sisvime.app.entity.Cite;
import com.sisvime.app.repository.ICiteRepostiory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class CiteService {

    @Autowired
    private ICiteRepostiory citeRepostiory;

    public ArrayList<Cite> getAll() {
        return (ArrayList<Cite>) citeRepostiory.findAll();
    }

    public Optional<Cite> getById(Long id) {
        return citeRepostiory.findById(id);
    }

    public Cite create(Cite cite) {
        return citeRepostiory.save(cite);
    }
}
