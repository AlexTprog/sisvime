package com.sisvime.app.models.Dao;

import com.sisvime.app.models.entity.Especialidad;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

public interface IEspecialidadDao extends PagingAndSortingRepository<Especialidad, Long> {

}
