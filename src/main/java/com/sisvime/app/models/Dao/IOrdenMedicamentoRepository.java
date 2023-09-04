package com.sisvime.app.models.Dao;

import com.sisvime.app.models.entity.OrdenMedicamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrdenMedicamentoRepository extends JpaRepository<OrdenMedicamento, Integer> {
}
