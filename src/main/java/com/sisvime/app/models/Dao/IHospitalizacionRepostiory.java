package com.sisvime.app.models.Dao;

import com.sisvime.app.models.entity.Hospitalizacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IHospitalizacionRepostiory extends JpaRepository<Hospitalizacion, Integer> {
}
