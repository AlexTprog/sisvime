package com.sisvime.app.models.Service.Imp;

import com.sisvime.app.models.Dao.IEnfermedadDao;
import com.sisvime.app.models.Service.IEnfermedadService;
import com.sisvime.app.models.entity.Enfermedad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EnfermedadServiceImpl implements IEnfermedadService {

    @Autowired
    private IEnfermedadDao enferdao;

    @Override
    public List<Enfermedad> findall() {

        return (List<Enfermedad>) enferdao.findAll();
    }

    @Override
    @Transactional
    public void save(Enfermedad enfermedad) {
        enferdao.save(enfermedad);

    }

    @Override
    @Transactional(readOnly = true)
    public Enfermedad findOne(int id) {

        return enferdao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void delete(int id) {
        enferdao.deleteById(id);

    }

    @Override
    @Transactional(readOnly = true)
    public Page<Enfermedad> findAllPage(Pageable pagueable) {

        return enferdao.findAll(pagueable);
    }

}
