package com.sisvime.app.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table
@Getter
@Setter
public class Driver extends User {


    private String CodeLicense;

    private Date ExpirationLicenseDay;

    @OneToOne
    private Vehicle Vehicle;

    @OneToOne(mappedBy = "Driver", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private VehicleAssignment Assignment;

    public void VehicleAssignment(Vehicle vehicle) {
        if (getAssignment() != null) {
            getAssignment().setEndDate(LocalDateTime.now());
        }
        setAssignment(new VehicleAssignment(vehicle,this));

    }
}
