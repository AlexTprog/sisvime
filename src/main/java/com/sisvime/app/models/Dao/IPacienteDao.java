package com.sisvime.app.models.Dao;


import com.sisvime.app.models.entity.Paciente;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IPacienteDao extends CrudRepository<Paciente, Long> {

    @Query("select p from Paciente p where p.nombre like %?1%")
    public List<Paciente> findByNombre(String term);

    //public List<Paciente> findByNombreLikeIgnoreCase(String term);
}

