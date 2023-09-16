package com.sisvime.app.models.Service;

import com.sisvime.app.models.entity.Medicamento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IMedicamentoService {

    public List<Medicamento> listarmedicamento();

    public Page<Medicamento> findAllPage(Pageable pagueable);

    public void save(Medicamento medicamento);

    public Medicamento findOne(int id);

    public void delete(int id);

}
