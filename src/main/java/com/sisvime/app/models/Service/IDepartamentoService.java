package com.sisvime.app.models.Service;


import com.sisvime.app.models.entity.Departamento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface IDepartamentoService {


    public List<Departamento> findAll();

    public Page<Departamento> findAll(Pageable pageable);

    public void save(Departamento departamento);

    public Departamento findOne(Long id);

    public void delete(Long id);
}
