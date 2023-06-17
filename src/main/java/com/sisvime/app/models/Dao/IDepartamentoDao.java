package com.sisvime.app.models.Dao;


import com.sisvime.app.models.entity.Departamento;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface IDepartamentoDao extends PagingAndSortingRepository<Departamento, Long> {

}
