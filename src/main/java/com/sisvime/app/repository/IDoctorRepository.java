package com.sisvime.app.repository;

import com.sisvime.app.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDoctorRepository extends JpaRepository<Doctor, Long> {
}
