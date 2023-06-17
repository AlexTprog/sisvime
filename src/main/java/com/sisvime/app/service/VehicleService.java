package com.sisvime.app.service;

import com.sisvime.app.entity.Vehicle;
import com.sisvime.app.repository.IVehicleRepository;
import com.sisvime.app.share.VehicleStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class VehicleService {

    @Autowired
    private IVehicleRepository vehicleRepository;

    public ArrayList<Vehicle> getAll() {
        return (ArrayList<Vehicle>) vehicleRepository.findAll();
    }

    public Page<Vehicle> getAll(Pageable pageable) {
        return vehicleRepository.findAll(pageable);
    }

    public Optional<Vehicle> getById(Long id) {
        return vehicleRepository.findById(id);
    }

    public Vehicle create(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    public void delete(Long id) {
        vehicleRepository.deleteById(id);
    }


    public void active(Long id) {
        var dbVehicle = getById(id).orElse(null);
        dbVehicle.setStatus(VehicleStatus.Enable);
    }

    public void block(Long id) {
        var dbVehicle = getById(id).orElse(null);
        dbVehicle.setStatus(VehicleStatus.Disable);
    }

}
