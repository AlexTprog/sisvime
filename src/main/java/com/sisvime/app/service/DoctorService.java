package com.sisvime.app.service;

import com.sisvime.app.entity.Doctor;
import com.sisvime.app.repository.IDoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class DoctorService {
    @Autowired
    private IDoctorRepository doctorRepository;

    public Doctor create(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    public Optional<Doctor> getById(Long id) {
        return doctorRepository.findById(id);
    }

    public ArrayList<Doctor> getAll() {
        return (ArrayList<Doctor>) doctorRepository.findAll();
    }

}
