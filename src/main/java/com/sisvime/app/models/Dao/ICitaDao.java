package com.sisvime.app.models.Dao;


import com.sisvime.app.models.entity.Cita;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICitaDao extends CrudRepository<Cita, Integer> {

}
