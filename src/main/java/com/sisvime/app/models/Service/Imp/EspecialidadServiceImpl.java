package com.sisvime.app.models.Service.Imp;

import com.sisvime.app.models.Dao.IEspecialidadDao;
import com.sisvime.app.models.Service.IEspecialidadService;
import com.sisvime.app.models.entity.Especialidad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EspecialidadServiceImpl implements IEspecialidadService {

    @Autowired
    private IEspecialidadDao especialidaddao;

    @Override
    @Transactional(readOnly = true)
    public List<Especialidad> findAll() {
        return (List<Especialidad>) especialidaddao.findAll();
    }

    @Override
    @Transactional
    public void save(Especialidad especialidad) {
        especialidaddao.save(especialidad);
    }

    @Override
    @Transactional(readOnly = true)
    public Especialidad findOne(Long id) {
        return especialidaddao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        especialidaddao.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Especialidad> findAll(Pageable pageable) {
        return especialidaddao.findAll(pageable);
    }

}
