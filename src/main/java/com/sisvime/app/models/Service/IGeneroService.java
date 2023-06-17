package com.sisvime.app.models.Service;

import com.sisvime.app.models.entity.Genero;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IGeneroService {

    public List<Genero> findAll();

    public Page<Genero> findAll(Pageable pageable);

    public void save(Genero genero);

    public Genero findOne(Long id);

    public void delete(Long id);
}
