package com.sisvime.app.models.Service;

import com.sisvime.app.models.entity.Especialidad;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IEspecialidadService {

    public List<Especialidad> findAll();

    public Page<Especialidad> findAll(Pageable pageable);

    public void save(Especialidad especialidad);

    public Especialidad findOne(Long id);

    public void delete(Long id);

}
