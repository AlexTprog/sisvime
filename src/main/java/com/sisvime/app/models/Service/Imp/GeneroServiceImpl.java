package com.sisvime.app.models.Service.Imp;

import com.sisvime.app.models.Dao.IGeneroDao;
import com.sisvime.app.models.Service.IGeneroService;
import com.sisvime.app.models.entity.Genero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GeneroServiceImpl implements IGeneroService {

    @Autowired
    private IGeneroDao generodao;

    @Override
    @Transactional(readOnly = true)
    public List<Genero> findAll() {

        return (List<Genero>) generodao.findAll();
    }

    @Override
    @Transactional
    public void save(Genero genero) {
        generodao.save(genero);

    }

    @Override
    @Transactional(readOnly = true)
    public Genero findOne(Long id) {

        return generodao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        generodao.deleteById(id);

    }

    @Override
    @Transactional(readOnly = true)
    public Page<Genero> findAll(Pageable pagueable) {

        return generodao.findAll(pagueable);
    }


}
