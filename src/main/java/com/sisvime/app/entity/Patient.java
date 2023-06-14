package com.sisvime.app.entity;

import com.sisvime.app.entity.users.User;
import com.sisvime.app.share.BloodType;
import com.sisvime.app.share.RHType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Random;

@Entity
@Table
@Getter
@Setter
public class Patient extends User {

    @Column(unique = true)
    private String NSA;
    private Integer Weigth;//Gramos
    private Integer Heigth;//Centimeter
    private BloodType Blood;
    private RHType RhFactor;
    private String Observation;
    private String Relationship;
    private String MedicalCondition;
    private String Allegy;

    public Patient() {
        this.NSA = generateNSA();
    }

    private String generateNSA() {
        var random = new Random();
        var codigo = new StringBuilder();

        for (int i = 0; i < 6; i++) {
            int digito = random.nextInt(10); // Genera un número aleatorio entre 0 y 9
            codigo.append(digito);
        }

        return codigo.toString();
    }
}
