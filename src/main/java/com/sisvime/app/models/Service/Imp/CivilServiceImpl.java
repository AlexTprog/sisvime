package com.sisvime.app.models.Service.Imp;

import com.sisvime.app.models.Dao.ICivilDao;
import com.sisvime.app.models.Service.ICivilService;
import com.sisvime.app.models.entity.Civil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CivilServiceImpl implements ICivilService {

    @Autowired
    private ICivilDao civildao;

    @Override
    @Transactional(readOnly = true)
    public List<Civil> findAll() {

        return (List<Civil>) civildao.findAll();
    }

    @Override
    @Transactional
    public void save(Civil civil) {
        civildao.save(civil);

    }

    @Override
    @Transactional(readOnly = true)
    public Civil findOne(Long id) {

        return civildao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        civildao.deleteById(id);

    }

    @Override
    @Transactional(readOnly = true)
    public Page<Civil> findAll(Pageable pagueable) {

        return civildao.findAll(pagueable);
    }


}
