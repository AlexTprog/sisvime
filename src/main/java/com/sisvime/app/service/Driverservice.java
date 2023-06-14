package com.sisvime.app.service;

import com.sisvime.app.entity.Driver;
import com.sisvime.app.repository.IDriverRepostiory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class Driverservice {

    @Autowired
    private IDriverRepostiory driverRepostiory;

    public ArrayList<Driver> getAll() {
        return (ArrayList<Driver>) driverRepostiory.findAll();
    }

    public Driver create(Driver Driver) {
        return driverRepostiory.save(Driver);
    }

    public Optional<Driver> getById(Long id) {
        return driverRepostiory.findById(id);
    }


}
