package com.sisvime.app.entity;

import com.sisvime.app.share.VehicleStatus;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Table
@Entity
@Getter
@Setter
public class Vehicle {
    @NonNull
    @Id
    @GeneratedValue
    private Long Id;

    private String Plate;

    private String Model;

    private String Color;

    private String TypeFuel;

    private Integer Axes;

    private Date DateOfManufacture;

    private VehicleStatus Status;

    @OneToMany(mappedBy = "Vehicle", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<VehicleAssignment> AssigmentHistory = new ArrayList<VehicleAssignment>();

    @OneToOne
    private Driver Driver;

    public void AssignDriver(Driver driver) {
        var assignment = new VehicleAssignment(this, driver);
        getAssigmentHistory().add(assignment);
    }
}
