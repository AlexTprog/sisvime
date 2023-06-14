package com.sisvime.app.repository;

import com.sisvime.app.entity.Medicament;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMedicamentRepostiory extends JpaRepository<Medicament, Long> {
}
