package com.sisvime.app.models.Dao;

import com.sisvime.app.models.entity.Cama;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICamaRepository extends JpaRepository<Cama, Integer> {
}
