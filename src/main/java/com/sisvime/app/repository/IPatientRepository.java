package com.sisvime.app.repository;

import com.sisvime.app.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IPatientRepository extends JpaRepository<Patient, Long> {
    List<Patient> findByNameContaining(String name);
}
