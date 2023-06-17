package com.sisvime.app.models.Service.Imp;

import com.sisvime.app.models.Dao.IMedicamentoDao;
import com.sisvime.app.models.Service.IMedicamentoService;
import com.sisvime.app.models.entity.Medicamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MedicamentoServiceImpl implements IMedicamentoService {

    @Autowired
    private IMedicamentoDao medicdao;

    @Override
    public List<Medicamento> listarmedicamento() {

        return (List<Medicamento>) medicdao.findAll();
    }

    @Override
    @Transactional
    public void save(Medicamento medicamento) {
        medicdao.save(medicamento);

    }

    @Override
    @Transactional(readOnly = true)
    public Medicamento findOne(int id) {

        return medicdao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void delete(int id) {
        medicdao.deleteById(id);

    }

    @Override
    @Transactional(readOnly = true)
    public Page<Medicamento> findAllPage(Pageable pagueable) {

        return medicdao.findAll(pagueable);
    }


}
