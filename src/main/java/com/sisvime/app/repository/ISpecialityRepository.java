package com.sisvime.app.repository;

import com.sisvime.app.entity.Speciality;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISpecialityRepository extends JpaRepository<Speciality, Long> {
}
