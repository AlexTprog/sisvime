package com.sisvime.app.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table
@Getter
@Setter
public class VehicleAssignment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Vehicle Vehicle;

    @ManyToOne
    private Driver Driver;

    private LocalDateTime EndDate;
    private LocalDateTime StartDate;

    public VehicleAssignment(Vehicle vehicle, Driver driver) {
        Vehicle = vehicle;
        Driver = driver;
        StartDate = LocalDateTime.now();
    }
}
