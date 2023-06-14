package com.sisvime.app.repository;

import com.sisvime.app.entity.Nurse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface INurseRepostiory extends JpaRepository<Nurse, Long> {
}
