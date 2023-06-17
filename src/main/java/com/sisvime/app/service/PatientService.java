package com.sisvime.app.service;

import com.sisvime.app.entity.Patient;
import com.sisvime.app.repository.IPatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PatientService {
    @Autowired
    private IPatientRepository patientRepository;

    public ArrayList<Patient> getAll() {
        return (ArrayList<Patient>) patientRepository.findAll();
    }

    public Patient getById(Long id) {
        return patientRepository.findById(id).orElse(null);
    }

    public Patient create(Patient Patient) {
        return patientRepository.save(Patient);
    }

    public void delete(Long id) {
        patientRepository.deleteById(id);
    }

    //TODO implementar getall con ordering
    public List<Patient> searchByName(String name) {
        return patientRepository.findByNameContaining(name);
    }
}
