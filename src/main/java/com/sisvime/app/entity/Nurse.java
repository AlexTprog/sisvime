package com.sisvime.app.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table
@Getter
@Setter
public class Nurse extends User {
    private Boolean IsTechnician;
    private String Description;
}
