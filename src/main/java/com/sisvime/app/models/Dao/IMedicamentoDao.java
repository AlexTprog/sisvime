package com.sisvime.app.models.Dao;

import com.sisvime.app.models.entity.Medicamento;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface IMedicamentoDao extends PagingAndSortingRepository<Medicamento, Integer> {

}
