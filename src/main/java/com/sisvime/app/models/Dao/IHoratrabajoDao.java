package com.sisvime.app.models.Dao;

import com.sisvime.app.models.entity.Horatrabajo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IHoratrabajoDao extends CrudRepository<Horatrabajo, Integer> {

}
