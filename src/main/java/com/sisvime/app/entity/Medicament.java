package com.sisvime.app.entity;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
@Getter
@Setter
public class Medicament {
    @Id
    @GeneratedValue
    private Long Id;

    @NotNull
    private String Name;

    private String Description;

    private Integer Quantity;

}
