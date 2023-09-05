package com.sisvime.app.models.Dao;

import com.sisvime.app.models.entity.Orden;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrdenRepository extends JpaRepository<Orden, Integer> {
}
