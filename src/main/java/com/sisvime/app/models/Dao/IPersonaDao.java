package com.sisvime.app.models.Dao;

import com.sisvime.app.models.entity.Personal;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPersonaDao extends CrudRepository<Personal, Long> {

}
