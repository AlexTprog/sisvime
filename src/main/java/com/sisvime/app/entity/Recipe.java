package com.sisvime.app.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@Getter
@Setter
public class Recipe {

    @Id
    @GeneratedValue
    private Long Id;

    @OneToOne
    private Cite Cite;

    protected LocalDateTime Date;

    @ManyToMany
    private List<Medicament> Medicaments = new ArrayList<Medicament>();

}
