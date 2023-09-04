package com.sisvime.app.models.Service.Imp;

import com.sisvime.app.models.Dao.IOrdenMedicamentoRepository;
import com.sisvime.app.models.entity.OrdenMedicamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdenMedicamentoService {
    @Autowired
    private IOrdenMedicamentoRepository ordenMedicamentoRepository;

    public List<OrdenMedicamento> findAll(){
        return ordenMedicamentoRepository.findAll();
    }

    public OrdenMedicamento create(OrdenMedicamento ordenMedicamento){
        return ordenMedicamentoRepository.save(ordenMedicamento);
    }

    public OrdenMedicamento getById(Integer id){
        return ordenMedicamentoRepository.findById(id).orElse(null);
    }

    public void delete(Integer id){
        ordenMedicamentoRepository.deleteById(id);
    }
}
