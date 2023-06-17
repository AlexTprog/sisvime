package com.sisvime.app.models.Service;

import com.sisvime.app.models.entity.Civil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICivilService {

    public List<Civil> findAll();

    public Page<Civil> findAll(Pageable pageable);

    public void save(Civil civil);

    public Civil findOne(Long id);

    public void delete(Long id);
}
