package com.sisvime.app.models.Service;


import com.sisvime.app.models.entity.Enfermedad;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IEnfermedadService {

    public List<Enfermedad> findall();

    public Page<Enfermedad> findAllPage(Pageable pagueable);

    public void save(Enfermedad enfermedad);

    public Enfermedad findOne(int id);

    public void delete(int id);
}
