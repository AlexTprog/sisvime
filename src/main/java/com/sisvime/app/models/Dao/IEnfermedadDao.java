package com.sisvime.app.models.Dao;


import com.sisvime.app.models.entity.Enfermedad;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface IEnfermedadDao extends PagingAndSortingRepository<Enfermedad, Integer> {

}
