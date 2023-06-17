package com.sisvime.app.models.Service.Imp;


import com.sisvime.app.models.Dao.IDepartamentoDao;
import com.sisvime.app.models.Service.IDepartamentoService;
import com.sisvime.app.models.entity.Departamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DepartamentoServiceImpl implements IDepartamentoService {

    @Autowired
    private IDepartamentoDao depadao;

    @Override
    @Transactional(readOnly = true)
    public List<Departamento> findAll() {
        return (List<Departamento>) depadao.findAll();
    }

    @Override
    @Transactional
    public void save(Departamento departamento) {
        depadao.save(departamento);

    }

    @Override
    @Transactional(readOnly = true)
    public Departamento findOne(Long id) {
        return depadao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        depadao.deleteById(id);

    }

    @Override
    @Transactional(readOnly = true)
    public Page<Departamento> findAll(Pageable pageable) {

        return depadao.findAll(pageable);
    }


}
