package com.sisvime.app.service;

import com.sisvime.app.entity.Patient;
import com.sisvime.app.repository.IPatientRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Optional;

public class PatientService {
    @Autowired
    private IPatientRepository patientRepository;

    public ArrayList<Patient> getAll() {
        return (ArrayList<Patient>) patientRepository.findAll();
    }

    public Optional<Patient> getById(Long id) {
        return patientRepository.findById(id);
    }

    public Patient create(Patient Patient) {
        return patientRepository.save(Patient);
    }
}
